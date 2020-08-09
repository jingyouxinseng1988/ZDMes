package com.zhengda.platform.service;


import com.zhengda.platform.entity.Card;

/**
 * 服务接口。
 */
public interface CardService extends BaseService<Card> {
    Card getByCardCode(String plantCode, String cardCode, Long employeeId);
}
