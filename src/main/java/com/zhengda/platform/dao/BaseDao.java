package com.zhengda.platform.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    void insert(T t);

    void insertList(List<T> list);

    int update(T t);

    T getById(Long id);

    Long getCount(Map<String, Object> map);

    List<T> getList(Map<String, Object> map);

    Integer deleteById(Long id);


}
