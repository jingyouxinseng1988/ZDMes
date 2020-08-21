package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterEmployee {
    @NotBlank(message = "名字不能为空")
    private String name;//名字
    @NotBlank(message = "工号不能为空")
    private String employeeNo;//工号
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;//工厂编码
    @NotBlank(message = "密码不能为空")
    private String password;//密码

    private Integer role;
    private Long id; //唯一标识

}
