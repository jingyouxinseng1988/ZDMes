package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.UserOrderDetailDao;
import com.zhengda.platform.entity.UserOrderDetail;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.UserOrderDetailQueryBo;
import com.zhengda.platform.service.UserOrderDetailService;
import com.zhengda.platform.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("userOrderDetailService")
public class UserOrderDetailServiceImpl implements UserOrderDetailService {

    @Resource
    private UserOrderDetailDao userOrderDetailDao;

    @Override
    public void add(UserOrderDetail t) {
        userOrderDetailDao.insert(t);
    }

    @Override
    public void addList(List<UserOrderDetail> list) {
        userOrderDetailDao.insertList(list);
    }

    @Override
    public int update(UserOrderDetail t) {
        return userOrderDetailDao.update(t);
    }

    @Override
    public UserOrderDetail getById(Long id) {
        return userOrderDetailDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return userOrderDetailDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return userOrderDetailDao.getCount(queryBo.getMap());
    }

    @Override
    public List<UserOrderDetail> getList(BaseQueryBo queryBo) {
        return userOrderDetailDao.getList(queryBo.getMap());
    }

    @Override
    public List<UserOrderDetail> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        UserOrderDetailQueryBo queryBo = new UserOrderDetailQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<UserOrderDetail> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, UserOrderDetail> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, UserOrderDetail> map = new HashMap<>();
        List<UserOrderDetail> list = getListByIds(ids);
        for (UserOrderDetail entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

    @Override
    public List<UserOrderDetail> getListByUnallocated(String plantCode, Long startTime, Long endTime) {
        Date startTimeByDay = startTime == null ? null : DateUtils.getStartTimeByDay(new Date(startTime));
        Date endTimeByDay = startTime == null ? null : DateUtils.getEndTimeByDay(new Date(endTime));
        return userOrderDetailDao.getListByUnallocated2(plantCode, startTimeByDay, endTimeByDay);
    }

    public List<UserOrderDetail> getListByAllocated(String plantCode) {
        return userOrderDetailDao.getListByAllocated2(plantCode);
    }

}
