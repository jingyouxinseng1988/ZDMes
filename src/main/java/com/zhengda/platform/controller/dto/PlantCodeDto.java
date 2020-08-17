package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PlantCodeDto {
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    private Long startTime;
    private Long endTime;
    private Integer type;
    private Integer status;
}
