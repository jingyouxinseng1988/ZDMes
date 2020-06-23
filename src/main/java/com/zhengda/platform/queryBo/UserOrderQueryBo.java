package com.zhengda.platform.queryBo;


import java.util.Set;

/**
* 订单表 where 查询自定义封装，
*/
public class UserOrderQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
        }

        public void setProcessOrderNo(String processOrderNo) {
           put("processOrderNo", processOrderNo);
        }

        public void setProcessOrderType(String processOrderType) {
           put("processOrderType", processOrderType);
        }

        public void setScheduleStartDate(java.util.Date scheduleStartDate) {
           put("scheduleStartDate", scheduleStartDate);
        }

        public void setScheduleEndDate(java.util.Date scheduleEndDate) {
           put("scheduleEndDate", scheduleEndDate);
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
}
