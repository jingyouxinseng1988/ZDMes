package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.dao.TaskDao;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.TaskQueryBo;
import com.zhengda.platform.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskDao taskDao;

    @Override
    public void add(Task t) {
        taskDao.insert(t);
    }

    @Override
    public void addList(List<Task> list) {
        taskDao.insertList(list);
    }

    @Override
    public int update(Task t) {
        return taskDao.update(t);
    }

    @Override
    public Task getById(Long id) {
        return taskDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return taskDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return taskDao.getCount(queryBo.getMap());
    }

    @Override
    public List<Task> getList(BaseQueryBo queryBo) {
        return taskDao.getList(queryBo.getMap());
    }

    @Override
    public List<Task> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        TaskQueryBo queryBo = new TaskQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<Task> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, Task> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, Task> map = new HashMap<>();
        List<Task> list = getListByIds(ids);
        for (Task entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }



}
