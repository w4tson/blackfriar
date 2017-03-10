package com.blackfriar.testconfig;

import com.blackfriar.config.RestTemplateConfig;
import config.JacksonMapperConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@TestConfiguration
@EnableConfigurationProperties
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import({RestTemplateConfig.class, JacksonMapperConfig.class})
@Profile("test")
public class ClientTestConfig {
}
