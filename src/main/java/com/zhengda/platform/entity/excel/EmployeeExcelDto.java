package com.zhengda.platform.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EmployeeExcelDto {
    /**
     * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 0)
    private String groupName;
    @ExcelProperty(index = 1)
    private String employeeNo;
    @ExcelProperty(index = 2)
    private String name;
    @ExcelProperty(index = 3)
    private String phone;
    @ExcelProperty(index = 4)
    private String address;
    @ExcelProperty(index = 5)
    private String contractEndTime;
    @ExcelProperty(index = 6)
    private String contractStartTime;

    @ExcelProperty(index = 7)
    private String status;

    @ExcelProperty(index = 8)
    private String card;
    @ExcelProperty(index = 9)
    private String plantCode;


}
