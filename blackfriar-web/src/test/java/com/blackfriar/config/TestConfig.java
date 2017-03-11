package com.blackfriar.config;

import config.JacksonMapperConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "com.blackfriar.assemblers" })
@Import({RestTemplateConfig.class, JacksonMapperConfig.class})
@EnableWebMvc
public class TestConfig {

}
