package com.zhengda.platform.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @RequestMapping("/test")
    public String get() {


        try {
            int a = 10 / 0;
        } catch (Exception e) {
//            log.error("Failed to format {}", e.getMessage(), e);
            log.error(e.getMessage());
        }
        return "";
    }
}
