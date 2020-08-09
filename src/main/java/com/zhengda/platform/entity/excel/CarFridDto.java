package com.zhengda.platform.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CarFridDto {
    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 2)
    private String frid;
    @ExcelProperty(index = 0)
    private String uuid;
    @ExcelProperty(index = 1)
    private String barrelCarNo;

    public static void main(String[] args) {

    }
}
