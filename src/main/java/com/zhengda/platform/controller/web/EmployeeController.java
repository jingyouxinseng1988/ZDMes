package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.IdDto;
import com.zhengda.platform.controller.dto.LoginDto;
import com.zhengda.platform.controller.dto.RegisterEmployee;
import com.zhengda.platform.controller.dto.UpdateEmployee;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.queryBo.EmployeeQueryBo;
import com.zhengda.platform.queryBo.TaskQueryBo;
import com.zhengda.platform.service.EmployeeService;
import com.zhengda.platform.service.TaskService;
import com.zhengda.platform.util.MD5Utils;
import com.zhengda.platform.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TaskService taskService;

    @PostMapping(value = "/register")
    @Transactional
    public AjaxResult add(@Valid RegisterEmployee registerEmployee) {
        EmployeeQueryBo employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        employeeQueryBo.setPlantCode(registerEmployee.getPlantCode());
        employeeQueryBo.setEmployeeNo(registerEmployee.getEmployeeNo());
        Long count = employeeService.getCount(employeeQueryBo);
        if (count > 0) {
            return AjaxResult.error("员工号已经注册");
        }
        employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        employeeQueryBo.setPlantCode(registerEmployee.getPlantCode());
        count = employeeService.getCount(employeeQueryBo);
        if (count > 0) {
            return AjaxResult.error("电话号码已经注册");
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(registerEmployee, employee);
        employee.setPassword(MD5Utils.md5(registerEmployee.getPassword()));
        employeeService.add(employee);
        registerEmployee.setId(employee.getId());
        registerEmployee.setRole(0);
        return AjaxResult.success(registerEmployee);
    }

    @RequestMapping(value = "/login")
    @Transactional
    public AjaxResult login(@Valid LoginDto loginDto) {
        EmployeeQueryBo employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        employeeQueryBo.setPlantCode(loginDto.getPlantCode());
        employeeQueryBo.setEmployeeNo(loginDto.getEmployeeNo());
        List<Employee> list = employeeService.getList(employeeQueryBo);
        if (list.isEmpty()) {
            return AjaxResult.error("未注册");
        }
        Employee employee = list.get(0);
        if (!employee.getPassword().equals(MD5Utils.md5(loginDto.getPassword()))) {
            return AjaxResult.error("密码错误");
        }

        RegisterEmployee registerEmployee = new RegisterEmployee();
        BeanUtils.copyProperties(employee, registerEmployee);
        return AjaxResult.success(registerEmployee);
    }

    @RequestMapping(value = "/update")
    @Transactional
    public AjaxResult update(@Valid UpdateEmployee updateEmployee) {
        Employee employee = employeeService.getById(updateEmployee.getId());
        if (employee == null) {
            return AjaxResult.warn("id没有找到");
        }
        SpringUtil.copyNotNullProperties(updateEmployee, employee);
        if (!StringUtils.isEmpty(updateEmployee.getPassword())) {
            employee.setPassword(MD5Utils.md5(updateEmployee.getPassword()));
        }
        employeeService.update(employee);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/delete")
    @Transactional
    public AjaxResult delete(@Valid IdDto idDto) {
        Employee employee = employeeService.getById(idDto.getId());
        if (employee == null) {
            return AjaxResult.warn("id没有找到");
        }
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setEmployeeId(employee.getId());
        taskQueryBo.setStatus(1);
        List<Task> list = taskService.getList(taskQueryBo);
        if (!list.isEmpty()) {
            return AjaxResult.warn("有未完成的任务");
        }
        taskQueryBo.setStatus(0);
        list = taskService.getList(taskQueryBo);
        for (Task task : list) {
            task.setDeleted(Constants.DELETED_YES);
            task.setModifyTime(new Date());
            taskService.update(task);
        }
        employee.setDeleted(Constants.DELETED_YES);
        employee.setModifyTime(new Date());
        employeeService.update(employee);
        return AjaxResult.success("");
    }

}
