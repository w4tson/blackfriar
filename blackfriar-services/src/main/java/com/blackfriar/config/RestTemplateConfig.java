package com.blackfriar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.asList;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        RestTemplate restTemplate = new RestTemplate();

        //TODO : inject ClientHttpRequestFactory
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jackson2ObjectMapperBuilder.build());

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();

        Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter = new Jaxb2RootElementHttpMessageConverter();


        restTemplate.setMessageConverters(asList(jsonMessageConverter, stringHttpMessageConverter, jaxb2RootElementHttpMessageConverter));

        return restTemplate;
    }
}
