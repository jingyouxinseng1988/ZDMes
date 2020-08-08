package com.zhengda.platform.service;


import com.zhengda.platform.entity.Employee;

import java.util.List;

/**
* 服务接口。
*/
public interface EmployeeService extends BaseService<Employee> {
    List<Employee> getListByTaskId(Long taskId);

}
