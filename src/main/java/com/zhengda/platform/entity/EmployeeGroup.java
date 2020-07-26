package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class EmployeeGroup {


// ~~~~实体属性
        //  [主键]
            private Long id;

        // 组Id 
            private Long groupId;

        // 员工ID 
            private Long employeeId;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        //  
            private Integer deleted;

        // 工厂编码 
            private String plantCode;


}
