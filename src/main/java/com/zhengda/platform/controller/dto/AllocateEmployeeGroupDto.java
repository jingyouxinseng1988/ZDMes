package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AllocateEmployeeGroupDto {

    @NotNull(message = "组ID不能为空")
    private Long groupId ;
    @NotNull(message = "Id不能为空")
    private Long employeeId;


    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;


}
