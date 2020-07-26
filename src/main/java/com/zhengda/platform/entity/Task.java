package com.zhengda.platform.entity;

import lombok.Data;

import java.util.Date;

/**
 * 实体对象：任务表
 */
@Data
public class Task {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    // 订单号
    private String processOrderNo;

    // 生产时间
    private Date productionTime;

    // 订单类型
    private String processOrderType;

    // 计划开始时间
    private Date planStartTime;

    // 计划结束时间
    private Date planEndTime;

    // 产品类型
    private String productionType;

    // 产品名称
    private String productionName;

    // 产品编码
    private String productionCode;

    // 规格
    private String specifications;

    // 目标重量
    private java.math.BigDecimal targetWeight;

    // 完成重量
    private java.math.BigDecimal finshedWeight;

    // 批次号
    private String batchCode;

    // 员工编码
    private String employeeCode;

    // 员工id
    private Long employeeId;

    // 工位
    private String workPosition;

    // 客户名称
    private String customerName;

    // 工厂编码
    private String plantCode;

    //
    private Date createTime;

    //
    private Date modifyTime;

    //
    private Integer deleted;

    private Long orderDetailId;

    private Integer status;


}
