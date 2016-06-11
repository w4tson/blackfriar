package com.blackfriar;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by paulwatson on 28/03/2016.
 */
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebConfig extends WebMvcConfigurerAdapter {

    //@Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider("ex", new UriTemplate("http://www.example.com/rels/{rel}"));
    }



//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(converter());
//    }
//
//
//
//
//
//
//    @Bean
//    public MappingJackson2HttpMessageConverter converter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(mapper());
//        return converter;
//    }
//
//
//  /*
//  * Provides the Jackson ObjectMapper with custom configuration for our JSON serialization.
//  * @return The Jackson object mapper with non-null serialization configured
//  */
//  @Bean
//  public JacksonObjectMapper mapper() {
//      return new JacksonObjectMapper();
//  }
}

