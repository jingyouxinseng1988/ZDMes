package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class IdDto {
    @NotNull(message = "Id不能为空")
    private Long id;
}
