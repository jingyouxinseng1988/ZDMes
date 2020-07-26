package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateEmployee {

    private String name;
    private String phone;
    private String employeeNo;
    private String plantCode;
    private String password;
    private Integer role;
    @NotNull(message = "Id不能为空")
    private Long id;
}
