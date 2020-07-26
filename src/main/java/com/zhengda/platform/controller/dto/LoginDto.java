package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank(message = "电话不能为空")
    private String phone;
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    @NotBlank(message = "密码不能为空")
    private String password;
}
