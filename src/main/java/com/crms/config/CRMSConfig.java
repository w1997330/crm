package com.crms.config;

import com.crms.filter.CrmsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CRMSConfig extends WebMvcConfigurationSupport {

    /**
     * 跨域拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean registerAuthFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrmsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("filterCore");
        registration.setOrder(1);  //值越小，Filter越靠前。
        return registration;
    }
}
