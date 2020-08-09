package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.GroupDao;
import com.zhengda.platform.entity.Group;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.GroupQueryBo;
import com.zhengda.platform.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Override
    public void add(Group t) {
        groupDao.insert(t);
    }

    @Override
    public void addList(List<Group> list) {
        groupDao.insertList(list);
    }

    @Override
    public int update(Group t) {
        return groupDao.update(t);
    }

    @Override
    public Group getById(Long id) {
        return groupDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return groupDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return groupDao.getCount(queryBo.getMap());
    }

    @Override
    public List<Group> getList(BaseQueryBo queryBo) {
        return groupDao.getList(queryBo.getMap());
    }

    @Override
    public List<Group> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        GroupQueryBo queryBo = new GroupQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<Group> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, Group> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, Group> map = new HashMap<>();
        List<Group> list = getListByIds(ids);
        for (Group entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

    public Group getByName(String name, String plantCode) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(plantCode)) {
            return null;
        }
        GroupQueryBo queryBo = new GroupQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setName(name);
        queryBo.setPlantCode(plantCode);
        List<Group> list = this.getList(queryBo);
        return list.isEmpty() ? null : list.get(0);
    }

}
