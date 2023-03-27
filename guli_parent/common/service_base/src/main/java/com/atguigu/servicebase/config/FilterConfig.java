package com.atguigu.servicebase.config;


import com.atguigu.servicebase.filter.EduFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 过滤器控制
 */
@Configuration
public class FilterConfig {

    /**
     * 注册
     * @return
     */
    @Bean
    public FilterRegistrationBean<EduFilter> getFilterRegistrationBean(){
        FilterRegistrationBean<EduFilter> filterRegistrationBean = new FilterRegistrationBean<EduFilter>();
        filterRegistrationBean.setFilter(new EduFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/eduservice/*");
        filterRegistrationBean.setName("EduFilter");
        return filterRegistrationBean;
    }

}
