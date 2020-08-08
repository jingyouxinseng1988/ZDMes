package com.zhengda.platform.service;


import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.entity.TaskEmployee;
import com.zhengda.platform.entity.ext.TaskEmployeeExt;

import java.util.List;
import java.util.Set;

/**
 * 服务接口。
 */
public interface TaskEmployeeService extends BaseService<TaskEmployee> {
    void init(Task task, List<Employee> employeeList);

    List<TaskEmployeeExt> getByTaskIdSet(Set<Long> taskIdSet);
}
