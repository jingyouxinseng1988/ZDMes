package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class BarrelCarQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setBarrelCarCode(String barrelCarCode) {
           put("barrelCarCode", barrelCarCode);
        }

        public void setFillingTime(java.util.Date fillingTime) {
           put("fillingTime", fillingTime);
        }

        public void setBarrelCarFrid(String barrelCarFrid) {
           put("barrelCarFrid", barrelCarFrid);
        }

        public void setProcessOrderNo(String processOrderNo) {
           put("processOrderNo", processOrderNo);
        }

        public void setUpdateTime(java.util.Date updateTime) {
           put("updateTime", updateTime);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModfiyTime(java.util.Date modfiyTime) {
           put("modfiyTime", modfiyTime);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }

        public void setDestination(String destination) {
           put("destination", destination);
        }
}
