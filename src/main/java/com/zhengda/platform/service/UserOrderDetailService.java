package com.zhengda.platform.service;


import com.zhengda.platform.controller.UserOrderDtailDto;
import com.zhengda.platform.entity.UserOrderDetail;

import java.util.List;

/**
 * 用户订单详情表服务接口。
 */
public interface UserOrderDetailService extends BaseService<UserOrderDetail> {
    List<UserOrderDetail> getListByCondition(UserOrderDtailDto dto)

}
