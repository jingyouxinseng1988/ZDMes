package com.zhengda.platform.queryBo;


import java.util.Set;

/**
 * where 查询自定义封装，
 */
public class FridUuidQueryBo extends BaseQueryBo {
    public void setIds(Set<Long> ids) {
        put("ids", ids);
    }

    public void setId(Long id) {
        put("id", id);
    }

    public void setFrid(String frid) {
        put("frid", frid);
    }

    public void setUuid(String uuid) {
        put("uuid", uuid);
    }

    public void setUuidSet(Set<String> uuidSet) {
        put("uuidSet", uuidSet);
    }

}
