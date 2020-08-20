package com.zhengda.platform.service.impl;

import com.zhengda.platform.common.Constants;
import com.zhengda.platform.controller.dto.TaskDto;
import com.zhengda.platform.dao.TaskDao;
import com.zhengda.platform.entity.Task;
import com.zhengda.platform.queryBo.BaseQueryBo;
import com.zhengda.platform.queryBo.TaskQueryBo;
import com.zhengda.platform.service.TaskService;
import com.zhengda.platform.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


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


    public Map<Long, Task> getMapByOrderDetailIdSet(List<Task> list) {
        Map<Long, Task> map = new HashMap<>();
        for (Task entity : list) {
            map.put(entity.getOrderDetailId(), entity);
        }
        return map;
    }

    public Map<Long, Boolean> isHaveTAsk(Set<Long> orderDetailIds) {
        Map<Long, Boolean> map = new HashMap<>();
        if (orderDetailIds == null || orderDetailIds.isEmpty()) {
            return map;
        }
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setOrderDetailIdSet(orderDetailIds);
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        List<Task> list = this.getList(taskQueryBo);
        Map<Long, List<Task>> orderDetailMap = list.stream().collect(Collectors.groupingBy(Task::getOrderDetailId));

        for (Long orderDetail : orderDetailIds) {
            map.put(orderDetail, false);
            List<Task> tasks = orderDetailMap.get(orderDetail);
            if (tasks == null) {
                continue;
            }
            for (Task task : tasks) {
                if (task.getEmployeeId() == null || task.getEmployeeId().equals(0L)) {
                    continue;
                }
                map.put(orderDetail, true);
            }
        }
        return map;
    }

    public List<Task> getListByOrderDetailIdSet(Set<Long> orderDetailIdSet) {
        if (orderDetailIdSet.isEmpty()) {
            return new ArrayList<>();
        }
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setOrderDetailIdSet(orderDetailIdSet);
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        return getList(taskQueryBo);

    }

    public List<Task> getListByCondition(TaskDto dto) {
        Set<Integer> statusSet = new HashSet<>();
        if (dto.getStatus() != null) {
            statusSet.add(dto.getStatus());
            if (dto.getStatus().equals(3) || dto.getStatus().equals(2)) {
                statusSet.add(2);
                statusSet.add(3);
            }

        }
        Date startTimeByDay = dto.getStartTime() == null ? null : DateUtils.getStartTimeByDay(new Date(dto.getStartTime()));
        Date endTimeByDay = dto.getEndTime() == null ? null : DateUtils.getEndTimeByDay(new Date(dto.getEndTime()));
        TaskQueryBo taskQueryBo = new TaskQueryBo();
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        taskQueryBo.setPlantCode(dto.getPlantCode());
        taskQueryBo.setStatusSet(statusSet);
        taskQueryBo.setStartTime(startTimeByDay);
        taskQueryBo.setEndTime(endTimeByDay);
        taskQueryBo.setDeleted(Constants.DELETED_NO);
        return getList(taskQueryBo);

    }
}
