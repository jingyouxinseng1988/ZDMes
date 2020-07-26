package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateGroupDto {

    @NotBlank(message = "组名不能为空")
    private String name;
    @NotNull(message = "Id不能为空")
    private Long id;


}
