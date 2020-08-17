package com.zhengda.platform.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResGroupDto {

    private Long id;
    private String name;
    private Long parentId;

    private String plantCode;

    private String groupCode;

    private List<EmployeeDto> employeeList;

    private List<ResGroupDto> subGroup;


}
