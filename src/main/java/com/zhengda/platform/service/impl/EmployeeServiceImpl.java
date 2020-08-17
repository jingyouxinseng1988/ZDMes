package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.EmployeeDao;
import com.zhengda.platform.entity.Employee;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.EmployeeQueryBo;
import com.zhengda.platform.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public void add(Employee t) {
        employeeDao.insert(t);
    }

    @Override
    public void addList(List<Employee> list) {
        employeeDao.insertList(list);
    }

    @Override
    public int update(Employee t) {
        return employeeDao.update(t);
    }

    @Override
    public Employee getById(Long id) {
        return employeeDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return employeeDao.getCount(queryBo.getMap());
    }

    @Override
    public List<Employee> getList(BaseQueryBo queryBo) {
        return employeeDao.getList(queryBo.getMap());
    }

    @Override
    public List<Employee> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        EmployeeQueryBo queryBo = new EmployeeQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<Employee> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, Employee> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, Employee> map = new HashMap<>();
        List<Employee> list = getListByIds(ids);
        for (Employee entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }


    @Override
    public List<Employee> getListByTaskId(Long taskId) {
        return employeeDao.getListByTaskId(taskId);
    }

    public Employee getByEmployeeNo(String employeeNo, String plantCode) {
        if (StringUtils.isEmpty(employeeNo)) {
            return null;
        }
        EmployeeQueryBo employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setPlantCode(plantCode);
        employeeQueryBo.setEmployeeNo(employeeNo);
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        List<Employee> list = this.getList(employeeQueryBo);
        return list.isEmpty() ? null : list.get(0);
    }
    public Employee getByPhone(String phone, String plantCode) {
        if (StringUtils.isEmpty(phone)) {
            return null;
        }
        EmployeeQueryBo employeeQueryBo = new EmployeeQueryBo();
        employeeQueryBo.setPlantCode(plantCode);
        employeeQueryBo.setPhone(phone);
        employeeQueryBo.setDeleted(Constants.DELETED_NO);
        List<Employee> list = this.getList(employeeQueryBo);
        return list.isEmpty() ? null : list.get(0);
    }
}
