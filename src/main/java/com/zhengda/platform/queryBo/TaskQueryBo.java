package com.zhengda.platform.queryBo;


import java.util.Set;

/**
* 任务表 where 查询自定义封装，
*/
public class TaskQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setProcessOrderNo(String processOrderNo) {
           put("processOrderNo", processOrderNo);
        }

        public void setProductionTime(java.util.Date productionTime) {
           put("productionTime", productionTime);
        }

        public void setProcessOrderType(String processOrderType) {
           put("processOrderType", processOrderType);
        }

        public void setPlanStartTime(java.util.Date planStartTime) {
           put("planStartTime", planStartTime);
        }

        public void setPlanEndTime(java.util.Date planEndTime) {
           put("planEndTime", planEndTime);
        }

        public void setProductionType(String productionType) {
           put("productionType", productionType);
        }

        public void setProductionName(String productionName) {
           put("productionName", productionName);
        }

        public void setProductionCode(String productionCode) {
           put("productionCode", productionCode);
        }

        public void setSpecifications(String specifications) {
           put("specifications", specifications);
        }

        public void setTargetWeight(java.math.BigDecimal targetWeight) {
           put("targetWeight", targetWeight);
        }

        public void setFinishedWeight(java.math.BigDecimal finishedWeight) {
           put("finishedWeight", finishedWeight);
        }

        public void setBatchCode(String batchCode) {
           put("batchCode", batchCode);
        }

        public void setEmployeeCode(String employeeCode) {
           put("employeeCode", employeeCode);
        }

        public void setEmployeeId(Long employeeId) {
           put("employeeId", employeeId);
        }

        public void setWorkPosition(String workPosition) {
           put("workPosition", workPosition);
        }

        public void setCustomerName(String customerName) {
           put("customerName", customerName);
        }

        public void setPlantCode(String plantCode) {
           put("plantCode", plantCode);
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

        public void setOrderDetailId(Long orderDetailId) {
           put("orderDetailId", orderDetailId);
        }

        public void setStatus(Integer status) {
           put("status", status);
        }

        public void setPersonnelStation(String personnelStation) {
           put("personnelStation", personnelStation);
        }
}
