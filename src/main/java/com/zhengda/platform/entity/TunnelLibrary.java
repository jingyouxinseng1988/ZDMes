package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
 * 实体对象：隧道库
 */
@Data
public class TunnelLibrary {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    // 入库时间
    private java.util.Date enterTime;

    // 出库时间
    private java.util.Date outTime;

    // 订单号
    private String processOrderNo;

    // 产品类别
    private String productType;

    // 产品名称
    private String productDesc;

    // 产品code
    private String productCode;

    // 产品规格
    private String productSpecifications;

    // ID号(筐)
    private String idNo;

    // 批次号
    private String batchNo;

    // 重量
    private java.math.BigDecimal weight;

    // 工人Id
    private Long employeeId;

    // 工人姓名
    private String employeeName;

    //
    private java.util.Date createTime;

    //
    private java.util.Date modifyTime;

    //
    private Integer deleted;

    // 工厂编码
    private String plantCode;

    // 1入库 2 出库
    private Integer type;

    // 生产编号
    private String employeeNo;

    // 班组
    private String teamGroup;

    // 发源地
    private String source;

    // 目的地
    private String destination;

    // 数量
    private java.math.BigDecimal number;

    // 单位 [主键]
    private String unit;

    // 皮重
    private java.math.BigDecimal tare;

    // 袋数
    private java.math.BigDecimal bagNumber;

    // 产线
    private String productionLine;

    // 是否读取
    private Integer flag;

    // 上框时间
    private java.util.Date upperBasketTime;


}
