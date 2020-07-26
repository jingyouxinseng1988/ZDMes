package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.BarrelCarDao;
import com.zhengda.platform.entity.BarrelCar;
import com.zhengda.platform.queryBo.BarrelCarQueryBo;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.service.BarrelCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("barrelCarService")
public class BarrelCarServiceImpl implements BarrelCarService {

    @Resource
    private BarrelCarDao barrelCarDao;

    @Override
    public void add(BarrelCar t) {
        barrelCarDao.insert(t);
    }

    @Override
    public void addList(List<BarrelCar> list) {
        barrelCarDao.insertList(list);
    }

    @Override
    public int update(BarrelCar t) {
        return barrelCarDao.update(t);
    }

    @Override
    public BarrelCar getById(Long id) {
        return barrelCarDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return barrelCarDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return barrelCarDao.getCount(queryBo.getMap());
    }

    @Override
    public List<BarrelCar> getList(BaseQueryBo queryBo) {
        return barrelCarDao.getList(queryBo.getMap());
    }

    @Override
    public List<BarrelCar> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        BarrelCarQueryBo queryBo = new BarrelCarQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<BarrelCar> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, BarrelCar> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, BarrelCar> map = new HashMap<>();
        List<BarrelCar> list = getListByIds(ids);
        for (BarrelCar entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

}
