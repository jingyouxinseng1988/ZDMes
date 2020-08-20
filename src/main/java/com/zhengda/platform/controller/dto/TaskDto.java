package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TaskDto {
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    private Long startTime;
    private Long endTime;

    private Integer type;
    //    0 未开始 1 进行中 //2和3代表强制完成
    private Integer status;
}
