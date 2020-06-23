package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class CardQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
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

        public void setCardCode(String cardCode) {
           put("cardCode", cardCode);
        }

        public void setEmployeeNo(String employeeNo) {
           put("employeeNo", employeeNo);
        }

        public void setEmployeeId(Long employeeId) {
           put("employeeId", employeeId);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }
}
