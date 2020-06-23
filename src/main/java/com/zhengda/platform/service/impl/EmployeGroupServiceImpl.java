package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.EmployeGroupQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.EmployeGroupDao;
import com.zhengda.platform.entity.EmployeGroup;
import com.zhengda.platform.service.EmployeGroupService;
import java.util.*;



@Service("employeGroupService")
public class EmployeGroupServiceImpl  implements EmployeGroupService {

	@Resource
	private EmployeGroupDao employeGroupDao;

	@Override
	public void add(EmployeGroup t) {
       employeGroupDao.insert(t);
	}

    @Override
    public void addList(List<EmployeGroup> list) {
       employeGroupDao.insertList(list);
    }

	@Override
	public int update(EmployeGroup t) {
	   return employeGroupDao.update(t);
	}

	@Override
	public EmployeGroup getById(Long id) {
	   return employeGroupDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return employeGroupDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return employeGroupDao.getCount(queryBo.getMap());
	}

	@Override
	public List<EmployeGroup> getList(BaseQueryBo queryBo) {
		return employeGroupDao.getList(queryBo.getMap());
	}

    @Override
    public List<EmployeGroup> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            EmployeGroupQueryBo queryBo = new EmployeGroupQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<EmployeGroup> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, EmployeGroup> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, EmployeGroup> map = new HashMap<>();
        List<EmployeGroup> list = getListByIds(ids);
        for (EmployeGroup entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
