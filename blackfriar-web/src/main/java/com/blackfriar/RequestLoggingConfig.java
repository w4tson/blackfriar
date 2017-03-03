package com.blackfriar;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Created by paulwatson on 21/02/2017.
 */
@Configuration
public class RequestLoggingConfig {

    @Value("${server.requestLogging.pattern}")
    String pattern;

    @Bean
    @ConditionalOnProperty(name ="server.requestLogging.enabled")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestDumperFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns(pattern);
        return filterRegistrationBean;
    }
}
