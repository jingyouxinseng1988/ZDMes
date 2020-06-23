package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class Employee {


// ~~~~实体属性
        // 唯一标识 [主键]
            private Long id;

        // 名字 
            private String name;

        // 性别 
            private Integer sex;

        // 身份证 
            private String identityCard;

        // 电话 
            private String phone;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        // 是否删除;0 未删除 1 已经删除 
            private Integer deleted;

        // 结束时间 
            private Date endTime;

        // 工号 
            private String employeeNo;

        // 工厂编码 
            private String plantCode;


}
