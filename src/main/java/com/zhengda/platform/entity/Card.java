package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class Card {


// ~~~~实体属性
        //  [主键]
            private Long id;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        //  
            private Integer deleted;

        // 卡号 
            private String cardCode;

        // 工号 
            private String employeeNo;

        // 员工Id 
            private Long employeeId;

        // 工厂编码 
            private String plantCode;


}
