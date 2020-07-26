package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：订单表
*/
@Data
public class UserOrder {


// ~~~~实体属性
        //  [主键]
            private Long id;

        // 工厂代码 
            private String plantCode;

        // 订单号 
            private String processOrderNo;

        // 订单类型 
            private String processOrderType;

        // 订单开始时间 
            private Date scheduleStartDate;

        // 订单结束时间 
            private Date scheduleEndDate;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        // 删除 1 已经删除 0 未删除 
            private Integer deleted;


}
