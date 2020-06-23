package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.MachineQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.MachineDao;
import com.zhengda.platform.entity.Machine;
import com.zhengda.platform.service.MachineService;
import java.util.*;



@Service("machineService")
public class MachineServiceImpl  implements MachineService {

	@Resource
	private MachineDao machineDao;

	@Override
	public void add(Machine t) {
       machineDao.insert(t);
	}

    @Override
    public void addList(List<Machine> list) {
       machineDao.insertList(list);
    }

	@Override
	public int update(Machine t) {
	   return machineDao.update(t);
	}

	@Override
	public Machine getById(Long id) {
	   return machineDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return machineDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return machineDao.getCount(queryBo.getMap());
	}

	@Override
	public List<Machine> getList(BaseQueryBo queryBo) {
		return machineDao.getList(queryBo.getMap());
	}

    @Override
    public List<Machine> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            MachineQueryBo queryBo = new MachineQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<Machine> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, Machine> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, Machine> map = new HashMap<>();
        List<Machine> list = getListByIds(ids);
        for (Machine entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
