package com.zhengda.platform.queryBo;


import java.util.Set;

/**
 * 用户订单详情表 where 查询自定义封装，
 */
public class UserOrderDetailQueryBo extends BaseQueryBo {
    public void setIds(Set<Long> ids) {
        put("ids", ids);
    }

    public void setId(Long id) {
        put("id", id);
    }

    public void setOrderId(Long orderId) {
        put("orderId", orderId);
    }

    public void setShift(Integer shift) {
        put("shift", shift);
    }

    public void setMaterialCode(String materialCode) {
        put("materialCode", materialCode);
    }

    public void setMaterialDesc(String materialDesc) {
        put("materialDesc", materialDesc);
    }

    public void setMaterialGroup(String materialGroup) {
        put("materialGroup", materialGroup);
    }

    public void setOldMaterialCode(String oldMaterialCode) {
        put("oldMaterialCode", oldMaterialCode);
    }

    public void setProductionNo(String productionNo) {
        put("productionNo", productionNo);
    }

    public void setProductionNoLike(String productionNoLike) {
        put("productionNoLike", productionNoLike);
    }

    public void setProductionUnit(String productionUnit) {
        put("productionUnit", productionUnit);
    }

    public void setProductionQty(java.math.BigDecimal productionQty) {
        put("productionQty", productionQty);
    }

    public void setReadStatus(Integer readStatus) {
        put("readStatus", readStatus);
    }

    public void setUpdateTime(java.util.Date updateTime) {
        put("updateTime", updateTime);
    }

    public void setZverid(String zverid) {
        put("zverid", zverid);
    }

    public void setLineNo(String lineNo) {
        put("lineNo", lineNo);
    }

    public void setLineName(String lineName) {
        put("lineName", lineName);
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

    public void setFlag(String flag) {
        put("flag", flag);
    }

    public void setOperationphase(String operationphase) {
        put("operationphase", operationphase);
    }

    public void setOperationphaseName(String operationphaseName) {
        put("operationphaseName", operationphaseName);
    }

    public void setConfirmationNo(String confirmationNo) {
        put("confirmationNo", confirmationNo);
    }

    public void setMachine(String machine) {
        put("machine", machine);
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        put("numberOfEmployees", numberOfEmployees);
    }

    public void setOperationQty(Integer operationQty) {
        put("operationQty", operationQty);
    }

    public void setOperationUnit(String operationUnit) {
        put("operationUnit", operationUnit);
    }

    public void setBaseQty(java.math.BigDecimal baseQty) {
        put("baseQty", baseQty);
    }

    public void setDenominatorQty(Integer denominatorQty) {
        put("denominatorQty", denominatorQty);
    }

    public void setNumeratorQty(Integer numeratorQty) {
        put("numeratorQty", numeratorQty);
    }

    public void setLaborTime(java.math.BigDecimal laborTime) {
        put("laborTime", laborTime);
    }

    public void setLaborUnit(String laborUnit) {
        put("laborUnit", laborUnit);
    }

    public void setMachineTime(java.math.BigDecimal machineTime) {
        put("machineTime", machineTime);
    }

    public void setMachineUnit(String machineUnit) {
        put("machineUnit", machineUnit);
    }

    public void setItemNo(String itemNo) {
        put("itemNo", itemNo);
    }

    public void setComponentCode(String componentCode) {
        put("componentCode", componentCode);
    }

    public void setComponentDesc(String componentDesc) {
        put("componentDesc", componentDesc);
    }

    public void setComponentQty(java.math.BigDecimal componentQty) {
        put("componentQty", componentQty);
    }

    public void setComponentUnit(String componentUnit) {
        put("componentUnit", componentUnit);
    }

    public void setProdstorLocation(String prodstorLocation) {
        put("prodstorLocation", prodstorLocation);
    }

    public void setCostOperationphase(String costOperationphase) {
        put("costOperationphase", costOperationphase);
    }

    public void setCtrCode(String ctrCode) {
        put("ctrCode", ctrCode);
    }

    public void setReceivingFlag(String receivingFlag) {
        put("receivingFlag", receivingFlag);
    }

    public void setStageFlag(String stageFlag) {
        put("stageFlag", stageFlag);
    }

    public void setReplaceFlag(String replaceFlag) {
        put("replaceFlag", replaceFlag);
    }

    public void setWaterelectronic(String waterelectronic) {
        put("waterelectronic", waterelectronic);
    }

    public void setPlantCode(String plantCode) {
        put("plantCode", plantCode);
    }

    public void setProcessOrderNo(String processOrderNo) {
        put("processOrderNo", processOrderNo);
    }

    public void setProductionType(String productionType) {
        put("productionType", productionType);
    }

    public void setProductionTypeLike(String productionTypeLike) {
        put("productionTypeLike", productionTypeLike);
    }


    public void setProductionSpec(String productionSpec) {
        put("productionSpec", productionSpec);
    }

    public void setDestination(String destination) {
        put("destination", destination);
    }

    public void setTargetWeight(String targetWeight) {
        put("targetWeight", targetWeight);
    }

    public void setCustomerName(String customerName) {
        put("customerName", customerName);
    }

    public void setProductionName(String productionName) {
        put("productionName", productionName);
    }

    public void setStartTime(java.util.Date startTime) {
        put("endTime", startTime);
    }

    public void setEndTime(java.util.Date endTime) {
        put("startTime", endTime);
    }
}
