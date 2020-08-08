package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.TaskEmployeeDao;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.entity.TaskEmployee;
import com.zhengda.platform.entity.ext.TaskEmployeeExt;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.TaskEmployeeQueryBo;
import com.zhengda.platform.service.TaskEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("taskEmployeeService")
public class TaskEmployeeServiceImpl implements TaskEmployeeService {

    @Resource
    private TaskEmployeeDao taskEmployeeDao;

    @Override
    public void add(TaskEmployee t) {
        taskEmployeeDao.insert(t);
    }

    @Override
    public void addList(List<TaskEmployee> list) {
        taskEmployeeDao.insertList(list);
    }

    @Override
    public int update(TaskEmployee t) {
        return taskEmployeeDao.update(t);
    }

    @Override
    public TaskEmployee getById(Long id) {
        return taskEmployeeDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return taskEmployeeDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return taskEmployeeDao.getCount(queryBo.getMap());
    }

    @Override
    public List<TaskEmployee> getList(BaseQueryBo queryBo) {
        return taskEmployeeDao.getList(queryBo.getMap());
    }

    @Override
    public List<TaskEmployee> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        TaskEmployeeQueryBo queryBo = new TaskEmployeeQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<TaskEmployee> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, TaskEmployee> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, TaskEmployee> map = new HashMap<>();
        List<TaskEmployee> list = getListByIds(ids);
        for (TaskEmployee entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

    public void init(Task task, List<Employee> employeeList) {

        TaskEmployeeQueryBo taskEmployeeQueryBo = new TaskEmployeeQueryBo();
        taskEmployeeQueryBo.setTaskId(task.getId());
        List<TaskEmployee> list = getList(taskEmployeeQueryBo);

        Map<Long, TaskEmployee> employeeMap = new HashMap<>();
        for (TaskEmployee taskEmployee : list) {
            employeeMap.put(taskEmployee.getEmployeeId(), taskEmployee);
        }


        for (Employee employee : employeeList) {
            if (employeeMap.containsKey(employee.getId())) {
                employeeMap.remove(employee.getId());
                continue;
            }
            TaskEmployee taskEmployee = new TaskEmployee();
            taskEmployee.setEmployeeId(employee.getId());
            taskEmployee.setTaskId(task.getId());
            taskEmployee.setEmployeeNo(employee.getEmployeeNo());
            add(taskEmployee);
        }
        Iterator<Map.Entry<Long, TaskEmployee>> iterator = employeeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, TaskEmployee> next = iterator.next();
            TaskEmployee value = next.getValue();
            taskEmployeeDao.deletePhysicalById(value.getId());
        }


    }

    @Override
    public List<TaskEmployeeExt> getByTaskIdSet(Set<Long> taskIdSet) {
        return taskEmployeeDao.getByTaskIdSet(taskIdSet);
    }


}
