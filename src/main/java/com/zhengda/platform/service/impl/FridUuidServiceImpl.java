package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.FridUuidQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.FridUuidDao;
import com.zhengda.platform.entity.FridUuid;
import com.zhengda.platform.service.FridUuidService;
import java.util.*;



@Service("fridUuidService")
public class FridUuidServiceImpl  implements FridUuidService {

	@Resource
	private FridUuidDao fridUuidDao;

	@Override
	public void add(FridUuid t) {
       fridUuidDao.insert(t);
	}

    @Override
    public void addList(List<FridUuid> list) {
       fridUuidDao.insertList(list);
    }

	@Override
	public int update(FridUuid t) {
	   return fridUuidDao.update(t);
	}

	@Override
	public FridUuid getById(Long id) {
	   return fridUuidDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return fridUuidDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return fridUuidDao.getCount(queryBo.getMap());
	}

	@Override
	public List<FridUuid> getList(BaseQueryBo queryBo) {
		return fridUuidDao.getList(queryBo.getMap());
	}

    @Override
    public List<FridUuid> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            FridUuidQueryBo queryBo = new FridUuidQueryBo();
            queryBo.setIds(ids);
            List<FridUuid> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, FridUuid> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, FridUuid> map = new HashMap<>();
        List<FridUuid> list = getListByIds(ids);
        for (FridUuid entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
