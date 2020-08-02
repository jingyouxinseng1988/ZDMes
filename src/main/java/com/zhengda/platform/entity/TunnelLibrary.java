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
            private Date enterTime;

        // 出库时间 
            private Date outTime;

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
            private Integer idNo;

        // 批次号 
            private String batchNo;

        // 重量 
            private java.math.BigDecimal weight;

        // 工人Id 
            private Long employeeId;

        // 工人姓名 
            private String employeeName;

        //  
            private Date createTime;

        //  
            private Date modifyTime;

        //  
            private Integer deleted;

        // 工厂编码 
            private String plantCode;

        // 1入库 2 出库 
            private Integer type;


}
