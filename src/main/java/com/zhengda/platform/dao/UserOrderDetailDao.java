package com.zhengda.platform.dao;

import com.zhengda.platform.entity.UserOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserOrderDetailDao extends BaseDao<UserOrderDetail> {
    List<UserOrderDetail> getListByUnallocated(@Param("plantCode") String plantCode, @Param("startTime") Long startTime, @Param("endTime") Long endTime);
    List<UserOrderDetail> getListByUnallocated2(@Param("plantCode") String plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    List<UserOrderDetail> getListByTime(@Param("plantCode") String plantCode, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<UserOrderDetail> getListByAllocated(@Param("plantCode") String plantCode);
    List<UserOrderDetail> getListByAllocated2(@Param("plantCode") String plantCode,@Param("statusSet") Set<Integer> statusSet);

}
