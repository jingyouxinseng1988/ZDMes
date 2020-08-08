package com.zhengda.platform.dao;

import com.zhengda.platform.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao extends BaseDao<Employee> {

    List<Employee> getListByTaskId(@Param("taskId") Long taskId);

}
