package com.zhengda.platform.controller.web;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.*;
import com.zhengda.platform.domain.AjaxResult;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.EmployeeGroup;
import com.zhengda.platform.entity.Group;
import com.zhengda.platform.queryBo.EmployeeGroupQueryBo;
import com.zhengda.platform.queryBo.EmployeeQueryBo;
import com.zhengda.platform.queryBo.GroupQueryBo;
import com.zhengda.platform.service.EmployeeGroupService;
import com.zhengda.platform.service.EmployeeService;
import com.zhengda.platform.service.GroupService;
import com.zhengda.platform.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/group")
public class GroupController {
    @Resource
    private GroupService groupService;
    @Resource
    private EmployeeGroupService employeeGroupService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/add")
    @Transactional
    public AjaxResult add(@Valid GroupDto groupDto) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDto, group);
        if (StringUtils.isEmpty(group.getGroupCode())) {
            group.setGroupCode(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
        }
        groupService.add(group);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/delete")
    @Transactional
    public AjaxResult delete(@Valid IdDto idDto) {
        groupService.deleteById(idDto.getId());
        EmployeeGroupQueryBo employeeGroupQueryBo = new EmployeeGroupQueryBo();
        employeeGroupQueryBo.setDeleted(Constants.DELETED_NO);
        employeeGroupQueryBo.setGroupId(idDto.getId());
        List<EmployeeGroup> list = employeeGroupService.getList(employeeGroupQueryBo);
        for (EmployeeGroup eg : list) {
            employeeGroupService.deleteById(eg.getId());
        }
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/update")
    @Transactional
    public AjaxResult update(@Valid UpdateGroupDto updateGroupDto) {
        Group group = groupService.getById(updateGroupDto.getId());
        group.setName(updateGroupDto.getName());
        group.setModifyTime(new Date());
        groupService.update(group);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/allocate_employee_group")
    @Transactional
    public AjaxResult allocateEmployeeGroup(@Valid AllocateEmployeeGroupDto allocateEmployeeGroupDto) {

        Group group = groupService.getById(allocateEmployeeGroupDto.getGroupId());
        if (group.getParentId().equals(0L)) {
            return AjaxResult.warn("该组不能分配人员");
        }
        EmployeeGroupQueryBo groupQueryBo = new EmployeeGroupQueryBo();
        groupQueryBo.setGroupId(allocateEmployeeGroupDto.getGroupId());
        groupQueryBo.setEmployeeId(allocateEmployeeGroupDto.getEmployeeId());
        groupQueryBo.setDeleted(Constants.DELETED_NO);
        groupQueryBo.setPlantCode(allocateEmployeeGroupDto.getPlantCode());
        List<EmployeeGroup> list = employeeGroupService.getList(groupQueryBo);
        if (allocateEmployeeGroupDto.getGroupId().equals(0L) && !list.isEmpty()) {
            EmployeeGroup employeeGroup = list.get(0);
            employeeGroup.setDeleted(Constants.DELETED_YES);
            employeeGroupService.update(employeeGroup);
            return AjaxResult.success("");
        }

        if (!list.isEmpty()) {
            EmployeeGroup employeeGroup = list.get(0);
            employeeGroup.setEmployeeId(allocateEmployeeGroupDto.getEmployeeId());
            employeeGroup.setGroupId(allocateEmployeeGroupDto.getGroupId());
            employeeGroup.setModifyTime(new Date());
            employeeGroup.setPlantCode(allocateEmployeeGroupDto.getPlantCode());
            employeeGroupService.update(employeeGroup);
        } else {
            EmployeeGroup employeeGroup = new EmployeeGroup();
            employeeGroup.setEmployeeId(allocateEmployeeGroupDto.getEmployeeId());
            employeeGroup.setGroupId(allocateEmployeeGroupDto.getGroupId());
            employeeGroup.setModifyTime(new Date());
            employeeGroup.setPlantCode(allocateEmployeeGroupDto.getPlantCode());
            employeeGroupService.add(employeeGroup);
        }
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/list")
    public AjaxResult list(@Valid PlantCodeDto plantCodeDto) {
/**
 * 组
 */
        GroupQueryBo groupQueryBo = new GroupQueryBo();
        groupQueryBo.setDeleted(Constants.DELETED_NO);
        groupQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<Group> list = groupService.getList(groupQueryBo);
        Map<Long, List<Group>> parentIdMapGroup = new HashMap<>();
        if (!list.isEmpty()) {
            parentIdMapGroup = list.stream().collect(Collectors.groupingBy(Group::getParentId));
        }
/**
 *  员工
 */
        EmployeeQueryBo employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        employeeQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<Employee> employeeList = employeeService.getList(employeeQueryBo);
        Map<Long, Employee> idMapEmployee = new HashMap<>();
        if (!employeeList.isEmpty()) {
            idMapEmployee = employeeList.stream().collect(Collectors.toMap(Employee::getId, x -> x));
        }


        /**
         * 关系
         */
        EmployeeGroupQueryBo employeeGroupQueryBo = new EmployeeGroupQueryBo();
        employeeGroupQueryBo.setDeleted(Constants.DELETED_NO);
        employeeGroupQueryBo.setPlantCode(plantCodeDto.getPlantCode());
        List<EmployeeGroup> employeeGroupList = employeeGroupService.getList(employeeGroupQueryBo);
        Map<Long, List<Employee>> groupIdMapEmployees = new HashMap<>();
        for (EmployeeGroup eg : employeeGroupList) {
            Employee employee = idMapEmployee.get(eg.getEmployeeId());
            if (employee == null) {
                continue;
            }
            if (groupIdMapEmployees.containsKey(eg.getGroupId())) {
                groupIdMapEmployees.get(eg.getGroupId()).add(employee);
            } else {
                List<Employee> employees = new ArrayList<>();
                employees.add(employee);
                groupIdMapEmployees.put(eg.getGroupId(), employees);
            }
            idMapEmployee.remove(eg.getEmployeeId());
        }


        /**
         * 数据组装
         */

        List<Object> data = new ArrayList<>();
        if (!parentIdMapGroup.isEmpty()) {
            List<Group> groups = parentIdMapGroup.get(0L);
            for (Group group : groups) {
                ResGroupDto resGroupDto = new ResGroupDto();
                data.add(resGroupDto);
                BeanUtils.copyProperties(group, resGroupDto);
                resGroupDto.setEmployeeList(new ArrayList<>());
                resGroupDto.setSubGroup(new ArrayList<>());
                List<Group> subList = parentIdMapGroup.get(group.getId());

                if (subList != null) {
                    for (Group subGroup : subList) {
                        ResGroupDto subGroupDto = new ResGroupDto();
                        resGroupDto.getSubGroup().add(subGroupDto);
                        BeanUtils.copyProperties(subGroup, subGroupDto);
                        subGroupDto.setEmployeeList(new ArrayList<>());
                        List<Employee> employees = groupIdMapEmployees.get(subGroup.getId());
                        if (employees == null) {
                            continue;
                        }
                        List<EmployeeDto> employeeDtos = SpringUtil.copyListProperty(employees, EmployeeDto.class);
                        subGroupDto.setEmployeeList(employeeDtos);


                    }
                }


            }
        }
        /**
         * 添加默认组
         */
        List<Employee> defaultGroupEmployees = new ArrayList<>();
        for (Map.Entry<Long, Employee> map : idMapEmployee.entrySet()) {
            defaultGroupEmployees.add(map.getValue());
        }

        ResGroupDto defaultGroup = new ResGroupDto();
        defaultGroup.setGroupCode("defaultCode");
        defaultGroup.setId(0L);
        defaultGroup.setName("default");
        defaultGroup.setParentId(0L);
        defaultGroup.setPlantCode(plantCodeDto.getPlantCode());
        defaultGroup.setSubGroup(new ArrayList<>());
        defaultGroup.setEmployeeList(SpringUtil.copyListProperty(defaultGroupEmployees, EmployeeDto.class));
        data.add(defaultGroup);
        return AjaxResult.success(data);

    }

}
