package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class GroupDto {

    @NotBlank(message = "组名不能为空")
    private String name;
    @NotNull(message = "父Id不能为空")
    private Long parentId;

    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;

    private String groupCode;


}
