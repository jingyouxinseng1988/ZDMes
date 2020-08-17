package com.zhengda.platform.queryBo;


import java.util.Set;

/**
 * 隧道库 where 查询自定义封装，
 */
public class TunnelLibraryQueryBo extends BaseQueryBo {
    public void setIds(Set<Long> ids) {
        put("ids", ids);
    }

    public void setId(Long id) {
        put("id", id);
    }

    public void setEnterTime(java.util.Date enterTime) {
        put("enterTime", enterTime);
    }


    public void setStartTime(java.util.Date startTime) {
        put("startTime", startTime);
    }

    public void setEndTime(java.util.Date endTime) {
        put("endTime", endTime);
    }


    public void setOutTime(java.util.Date outTime) {
        put("outTime", outTime);
    }

    public void setProcessOrderNo(String processOrderNo) {
        put("processOrderNo", processOrderNo);
    }

    public void setProductType(String productType) {
        put("productType", productType);
    }

    public void setProductDesc(String productDesc) {
        put("productDesc", productDesc);
    }

    public void setProductCode(String productCode) {
        put("productCode", productCode);
    }

    public void setProductSpecifications(String productSpecifications) {
        put("productSpecifications", productSpecifications);
    }

    public void setIdNo(Integer idNo) {
        put("idNo", idNo);
    }

    public void setBatchNo(String batchNo) {
        put("batchNo", batchNo);
    }

    public void setWeight(java.math.BigDecimal weight) {
        put("weight", weight);
    }

    public void setEmployeeId(Long employeeId) {
        put("employeeId", employeeId);
    }

    public void setEmployeeName(String employeeName) {
        put("employeeName", employeeName);
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

    public void setType(Integer type) {
        put("type", type);
    }
}
