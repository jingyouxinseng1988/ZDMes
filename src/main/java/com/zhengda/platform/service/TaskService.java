package com.zhengda.platform.service;


import com.zhengda.platform.controller.dto.TaskDto;
import com.zhengda.platform.entity.Task;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 任务表服务接口。
 */
public interface TaskService extends BaseService<Task> {
    Map<Long, Task> getMapByOrderDetailIdSet(List<Task> list);

    List<Task> getListByOrderDetailIdSet(Set<Long> orderDetailIdSet);

    List<Task> getListByCondition(TaskDto dto);


    Map<Long, Boolean> isHaveTAsk(Set<Long> orderDetailIds);
}
