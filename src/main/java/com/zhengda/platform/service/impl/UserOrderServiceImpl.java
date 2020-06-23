package com.zhengda.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.UserOrderQueryBo;
import com.zhengda.platform.common.Constants;

import com.zhengda.platform.dao.UserOrderDao;
import com.zhengda.platform.entity.UserOrder;
import com.zhengda.platform.service.UserOrderService;
import java.util.*;



@Service("userOrderService")
public class UserOrderServiceImpl  implements UserOrderService {

	@Resource
	private UserOrderDao userOrderDao;

	@Override
	public void add(UserOrder t) {
       userOrderDao.insert(t);
	}

    @Override
    public void addList(List<UserOrder> list) {
       userOrderDao.insertList(list);
    }

	@Override
	public int update(UserOrder t) {
	   return userOrderDao.update(t);
	}

	@Override
	public UserOrder getById(Long id) {
	   return userOrderDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return userOrderDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return userOrderDao.getCount(queryBo.getMap());
	}

	@Override
	public List<UserOrder> getList(BaseQueryBo queryBo) {
		return userOrderDao.getList(queryBo.getMap());
	}

    @Override
    public List<UserOrder> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            UserOrderQueryBo queryBo = new UserOrderQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<UserOrder> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, UserOrder> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, UserOrder> map = new HashMap<>();
        List<UserOrder> list = getListByIds(ids);
        for (UserOrder entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
