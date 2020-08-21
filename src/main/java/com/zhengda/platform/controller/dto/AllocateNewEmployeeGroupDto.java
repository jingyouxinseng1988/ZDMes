package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AllocateNewEmployeeGroupDto {

    @NotNull(message = "组ID不能为空")
    private Long groupId;


    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;
    @NotBlank(message = "员工姓名")
    private String employeeNo;
    @NotBlank(message = "员工名字")
    private String name;


}
