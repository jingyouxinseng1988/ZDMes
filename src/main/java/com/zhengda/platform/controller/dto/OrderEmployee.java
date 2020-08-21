package com.zhengda.platform.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class OrderEmployee {
    @NotNull(message = "订单详情Id")
    private Long orderDetailId;

    private Long employeeId;
    @NotBlank(message = "工厂编码不能为空")
    private String plantCode;

    @NotBlank(message = "工厂编码编码不能为空")
    private String employeeNos;


}
