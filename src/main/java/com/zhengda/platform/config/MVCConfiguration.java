package com.zhengda.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

//    @Bean
//    public FilterRegistrationBean tokenFilter() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new TokenFilter());
//        registrationBean.addUrlPatterns("/api/v1/*");
//        // 值越小越靠前
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }


}
