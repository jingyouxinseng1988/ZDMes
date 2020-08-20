package com.zhengda.platform.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserOrderDtailDto {
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    private Long startTime;
    private Long endTime;
    private String  productionType;
    private String  productionNo;
}
