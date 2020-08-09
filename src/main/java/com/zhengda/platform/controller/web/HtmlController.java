package com.zhengda.platform.controller.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.zhengda.platform.entity.excel.CarFridDto;
import com.zhengda.platform.entity.excel.EmployeeDataListener;
import com.zhengda.platform.entity.excel.EmployeeExcelDto;
import com.zhengda.platform.entity.excel.IndexOrNameDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
public class HtmlController {

    @RequestMapping("/card")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/employee")
    public String employee(Model model) {
        return "employee";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {

        // 这里默认读取第一个sheet
        try {
            EasyExcel.read(file.getInputStream(), CarFridDto.class, new IndexOrNameDataListener()).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/employee_upload")
    @ResponseBody
    public String employee(@RequestParam("employeeUpload") MultipartFile file) {

        // 这里默认读取第一个sheet
        try {
            ExcelReaderSheetBuilder sheet = EasyExcel.read(file.getInputStream(), EmployeeExcelDto.class, new EmployeeDataListener()).sheet();
            sheet.doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
