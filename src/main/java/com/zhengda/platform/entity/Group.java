package com.zhengda.platform.entity;

import lombok.Data;
import java.util.Date;

/**
* 实体对象：
*/
@Data
public class Group {


// ~~~~实体属性
        //  [主键]
            private Long id;

        // 名字 
            private String name;

        // 是否删除;0 未删除 1 已经删除 
            private Integer deleted;

        // 创建时间 
            private Date createTime;

        // 修改时间 
            private Date modifyTime;

        // 父Id 
            private Long parentId;

        // 工厂编码 
            private String plantCode;

        // 编码 
            private String groupCode;


}
