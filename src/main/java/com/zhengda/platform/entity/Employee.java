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
    private String sex;

    // 身份证
    private String identityCard;

    // 电话
    private String phone;

    // 创建时间
    private java.util.Date createTime;

    // 修改时间
    private java.util.Date modifyTime;

    // 是否删除;0 未删除 1 已经删除
    private Integer deleted;

    // 结束时间
    private java.util.Date endTime;

    // 工号
    private String employeeNo;

    // 工厂编码
    private String plantCode;

    // 密码(md5)
    private String password;

    // 角色
    private Integer role;

    //
    private String status;

    // 地址
    private String address;

    // 合同开始时间
    private java.util.Date contractStartTime;

    // 合同结束时间
    private java.util.Date contractEndTime;


}
