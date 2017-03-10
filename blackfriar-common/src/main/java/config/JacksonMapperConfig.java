package config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.SpringHandlerInstantiator;

@Configuration
public class JacksonMapperConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return newJackson2ObjectMapperBuilder();
    }

    @Bean
    public ObjectMapper xmlMapper() {
        return newJackson2ObjectMapperBuilder().createXmlMapper(true).build();
    }

    private Jackson2ObjectMapperBuilder newJackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //TODO: timestamp serialization
        //builder.serializerByType(Instant.class, new Time)
        builder.applicationContext(applicationContext);
        builder.handlerInstantiator(new SpringHandlerInstantiator(applicationContext.getAutowireCapableBeanFactory()));
        return builder;
    }

    public static ObjectMapper defaultObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //TODO: timestamp serialization
        //builder.serializerByType(Instant.class, new Time)
        return builder.build();
    }

    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
