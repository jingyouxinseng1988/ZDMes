package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class BarrelCarDetail {


// ~~~~实体属性
        // 唯一标识 [主键]
            private Long id;

        // 桶车ID 
            private Long barrelCarId;

        // 产品名称 
            private String productName;

        // 产品code 
            private String productCode;

        // 规格 
            private String specifications;

        // 总重量 
            private java.math.BigDecimal totalWeight;

        // 计量单位 
            private String unit;

        // 工厂code 
            private String plantCode;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        // 是否删除 
            private Integer deleted;


}
