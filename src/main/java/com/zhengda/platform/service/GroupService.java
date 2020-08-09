package com.zhengda.platform.service;


import com.zhengda.platform.entity.Group;

/**
 * 服务接口。
 */
public interface GroupService extends BaseService<Group> {
    Group getByName(String name, String plantCode);
}
