package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CardDto {

    private Long id;
    @NotBlank(message = "cardCode不能为空")
    private String cardCode;
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    @NotNull(message = "员工Id不能为空")
    private Long employeeId;
    private String employeeNo;
}
