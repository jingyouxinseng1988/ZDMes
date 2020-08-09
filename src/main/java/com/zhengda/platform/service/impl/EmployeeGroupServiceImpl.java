package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.EmployeeGroupDao;
import com.zhengda.platform.entity.EmployeeGroup;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.EmployeeGroupQueryBo;
import com.zhengda.platform.service.EmployeeGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service("employeeGroupService")
public class EmployeeGroupServiceImpl implements EmployeeGroupService {

    @Resource
    private EmployeeGroupDao employeeGroupDao;

    @Override
    public void add(EmployeeGroup t) {
        employeeGroupDao.insert(t);
    }

    @Override
    public void addList(List<EmployeeGroup> list) {
        employeeGroupDao.insertList(list);
    }

    @Override
    public int update(EmployeeGroup t) {
        return employeeGroupDao.update(t);
    }

    @Override
    public EmployeeGroup getById(Long id) {
        return employeeGroupDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return employeeGroupDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return employeeGroupDao.getCount(queryBo.getMap());
    }

    @Override
    public List<EmployeeGroup> getList(BaseQueryBo queryBo) {
        return employeeGroupDao.getList(queryBo.getMap());
    }

    @Override
    public List<EmployeeGroup> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        EmployeeGroupQueryBo queryBo = new EmployeeGroupQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<EmployeeGroup> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, EmployeeGroup> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, EmployeeGroup> map = new HashMap<>();
        List<EmployeeGroup> list = getListByIds(ids);
        for (EmployeeGroup entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

    public EmployeeGroup getEmployeeGroup(Long employeeId, Long groupId, String plantCode) {

        if (employeeId == null || groupId == null || StringUtils.isEmpty(plantCode)) {
            return null;
        }
        EmployeeGroupQueryBo employeeGroupQueryBo = new EmployeeGroupQueryBo();
        employeeGroupQueryBo.setEmployeeId(employeeId);
        employeeGroupQueryBo.setGroupId(groupId);
        employeeGroupQueryBo.setPlantCode(plantCode);
        employeeGroupQueryBo.setDeleted(Constants.DELETED_NO);
        List<EmployeeGroup> list = this.getList(employeeGroupQueryBo);
        return list.isEmpty() ? null : list.get(0);
    }

}
