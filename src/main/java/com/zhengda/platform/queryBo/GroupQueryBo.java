package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class GroupQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setName(String name) {
           put("name", name);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }

        public void setParentId(Long parentId) {
           put("parentId", parentId);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }

        public void setGroupCode(String groupCode) {
           put("groupCode", groupCode);
        }
}
