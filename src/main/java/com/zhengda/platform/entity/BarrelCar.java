package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class BarrelCar {


// ~~~~实体属性
        //  [主键]
            private Long id;

        // 桶车编码 
            private String barrelCarCode;

        // 填装时间 
            private Date fillingTime;

        // 桶车frid 
            private String barrelCarFrid;

        // 订单编号 
            private String processOrderNo;

        // 更新时间 
            private Date updateTime;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modfiyTime;

        // 是否删除;0 未删除 1 已经删除 
            private Integer deleted;

        // 工厂编码 
            private String plantCode;

        // 目的地 
            private String destination;


}
