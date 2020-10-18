package com.zhengda.platform.service;


import com.zhengda.platform.entity.Employee;

import java.util.List;
import java.util.Set;

/**
 * 服务接口。
 */
public interface EmployeeService extends BaseService<Employee> {
    List<Employee> getListByTaskId(Long taskId);

    Employee getByEmployeeNo(String employeeNo, String plantCode);

    Employee getByPhone(String phone, String plantCode);
    List<Employee> getByEmployeeIds(Set<Long> employeeIds, String plantCode);
}
