package com.zhengda.platform.service;


import com.zhengda.platform.queryBo.BaseQueryBo;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 系统服务基础接口，定义最简单的增删改查功能。
 *
 * @param <T> 数据对象
 */
public interface BaseService<T> {
    /**
     * 添加接口，忽略Id
     *
     * @param t
     */
    void add(T t);

    void addList(List<T> list);

    /**
     * 更新接口必须有Id
     *
     * @param t
     * @return
     */
    int update(T t);

    T getById(Long id);

    Long getCount(BaseQueryBo queryBo);

    List<T> getList(BaseQueryBo queryBo);

    Integer deleteById(Long id);

    List<T> getListByIds(Set ids);

    Map<Long, T> getMapByIds(Set ids);
}