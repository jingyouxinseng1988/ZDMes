package com.zhengda.platform.entity;

import lombok.Data;

/**
 * 实体对象：
 */
@Data
public class TaskEmployee {


    //  [主键]
    private Long id;

    //
    private Long taskId;

    // 员工Id
    private Long employeeId;

    //
    private Integer deleted;

    //
    private java.util.Date createTime;

    //
    private java.util.Date modifyTime;

    // 工位
    private String personnelStation;

    // 员工编码
    private String employeeNo;


}
