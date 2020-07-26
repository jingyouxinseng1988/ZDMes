package com.zhengda.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class EmployeeQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setName(String name) {
           put("name", name);
        }

        public void setSex(Integer sex) {
           put("sex", sex);
        }

        public void setIdentityCard(String identityCard) {
           put("identityCard", identityCard);
        }

        public void setPhone(String phone) {
           put("phone", phone);
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

        public void setEndTime(java.util.Date endTime) {
           put("endTime", endTime);
        }

        public void setEmployeeNo(String employeeNo) {
           put("employeeNo", employeeNo);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }
}
