package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LibDto {
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    private Long startTime;
    private Long endTime;
    //1 入库 2 出库
    private Integer type;
    private Integer status;
    private String destination;
    private String productCode;
}
