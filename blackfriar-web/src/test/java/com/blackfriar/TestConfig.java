package com.blackfriar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.mock;

/**
 * Created by paulwatson on 28/03/2016.
 */
@Configuration
@ComponentScan(basePackages = { "com.blackfriar.assemblers", "com.blackfriar.controllers" })
@EnableWebMvc
public class TestConfig {


    @Bean
    public BeerService getBeerService() {
        return mock(BeerService.class);
    }
}
