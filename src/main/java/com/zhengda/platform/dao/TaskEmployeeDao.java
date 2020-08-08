package com.zhengda.platform.dao;

import com.zhengda.platform.entity.TaskEmployee;
import com.zhengda.platform.entity.ext.TaskEmployeeExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface TaskEmployeeDao extends BaseDao<TaskEmployee> {
    TaskEmployee deletePhysicalById(Long id);

    List<TaskEmployeeExt> getByTaskIdSet(@Param("taskIdSet") Set<Long> taskIdSet);
}
