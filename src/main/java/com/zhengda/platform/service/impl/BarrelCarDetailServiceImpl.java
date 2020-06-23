package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.BarrelCarDetailQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.BarrelCarDetailDao;
import com.zhengda.platform.entity.BarrelCarDetail;
import com.zhengda.platform.service.BarrelCarDetailService;
import java.util.*;



@Service("barrelCarDetailService")
public class BarrelCarDetailServiceImpl  implements BarrelCarDetailService {

	@Resource
	private BarrelCarDetailDao barrelCarDetailDao;

	@Override
	public void add(BarrelCarDetail t) {
       barrelCarDetailDao.insert(t);
	}

    @Override
    public void addList(List<BarrelCarDetail> list) {
       barrelCarDetailDao.insertList(list);
    }

	@Override
	public int update(BarrelCarDetail t) {
	   return barrelCarDetailDao.update(t);
	}

	@Override
	public BarrelCarDetail getById(Long id) {
	   return barrelCarDetailDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return barrelCarDetailDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return barrelCarDetailDao.getCount(queryBo.getMap());
	}

	@Override
	public List<BarrelCarDetail> getList(BaseQueryBo queryBo) {
		return barrelCarDetailDao.getList(queryBo.getMap());
	}

    @Override
    public List<BarrelCarDetail> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            BarrelCarDetailQueryBo queryBo = new BarrelCarDetailQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<BarrelCarDetail> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, BarrelCarDetail> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, BarrelCarDetail> map = new HashMap<>();
        List<BarrelCarDetail> list = getListByIds(ids);
        for (BarrelCarDetail entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
