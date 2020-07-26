package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class MachineQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setStatus(Integer status) {
           put("status", status);
        }

        public void setRunTime(String runTime) {
           put("runTime", runTime);
        }

        public void setCode(String code) {
           put("code", code);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }
}
