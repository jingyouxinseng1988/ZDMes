package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class Machine {


// ~~~~实体属性
        //  [主键]
            private Long id;

        //  
            private Integer status;

        //  
            private String runTime;

        //  
            private String code;

        //  
            private Date createTime;

        //  
            private Date modifyTime;

        //  
            private Integer deleted;

        // 工厂编码 
            private String plantCode;


}
