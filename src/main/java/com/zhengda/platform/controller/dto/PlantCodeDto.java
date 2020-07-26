package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlantCodeDto {
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
}
