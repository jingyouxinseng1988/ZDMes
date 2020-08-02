package com.zhengda.platform.controller.dto;

import lombok.Data;

@Data
public class UserOrderDetailDto {

    //  [主键]
    private Long id;

    // 订单Id
    private Long orderId;

    // 班次
    private Integer shift;

    // 物料编码
    private String materialCode;

    // 物料描述
    private String materialDesc;

    // 物料组
    private String materialGroup;

    // 旧物料编码
    private String oldMaterialCode;

    // 产品编号
    private String productionNo;

    // 计量单位
    private String productionUnit;

    // 计划数量
    private java.math.BigDecimal productionQty;

    // 已经读取  初始状态：1
    private Integer readStatus;


    // 线的主配方代码是CP01；5#线的主配方代码是CP00
    private String zverid;

    // 生产线编号
    private String lineNo;

    // 生产线名称
    private String lineName;


    // "A" = Add / "C" = Change / "D" = Delete
    private String flag;

    // 工序号
    private String operationphase;

    // 工序描述
    private String operationphaseName;

    // 工序报工的单据号
    private String confirmationNo;

    // 机器
    private String machine;

    // 雇员数量
    private Integer numberOfEmployees;

    // 工序数量
    private Integer operationQty;

    // 工序单位
    private String operationUnit;

    // 基本数量
    private java.math.BigDecimal baseQty;

    // 工序单位的分母 订单计划数量  几个  菜丁：1桶
    private Integer denominatorQty;

    // 工序单位的分子  订单计划量  包子：870Kg  半成品：1
    private Integer numeratorQty;

    // 人工工时
    private java.math.BigDecimal laborTime;

    // 人工工时单位
    private String laborUnit;

    // 机器工时
    private java.math.BigDecimal machineTime;

    // 机器工时单位
    private String machineUnit;

    // 行项目
    private String itemNo;

    // 原料代码
    private String componentCode;

    // 原料名称
    private String componentDesc;

    // 原料数量
    private java.math.BigDecimal componentQty;

    // 原料计量单位
    private String componentUnit;

    // 库存地点
    private String prodstorLocation;

    // 耗用工序号
    private String costOperationphase;

    // 控制码；半成品订单（肉馅） 当报工 时候， 标识为CP03 或CP04 的时候， 报工传给SAP物料移动数据里面，必须有 肉馅 移动类型101，这条数据  CP01代表收货标志
    private String ctrCode;

    // 入库数量汇报与否
    private String receivingFlag;

    // 工序标记 "0100"主工序，"Stage_Flag"=""空。其他工序"X"
    private String stageFlag;

    // 物料替代标志
    private String replaceFlag;

    // 水电费工序标志
    private String waterelectronic;

    // 工厂代码
    private String plantCode;

    // 订单编号
    private String processOrderNo;

    // 产品类型（主产品，副产品）
    private String productionType;

    private Long employeeId;
    private String employeeName;

    private Long scheduleStartDate;
    private Long scheduleEndDate;

    private String productionSpec;

    // 目的地
    private String destination;

    // 目标重量
    private String targetWeight;

    // 客户名字
    private String customerName;
    // 产品名称
    private String productionName;

    private java.math.BigDecimal finishedWeight;
    private String personnelStation;
}
