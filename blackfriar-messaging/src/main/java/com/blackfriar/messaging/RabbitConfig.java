package com.blackfriar.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

import static org.springframework.amqp.rabbit.config.RetryInterceptorBuilder.stateless;

@EnableRabbit
@Configuration
public class RabbitConfig {

    Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @Value("${spring.rabbitmq.host}")
    String rabbitHost;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Bean(name = "blackfriarContainerFactory")
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConcurrentConsumers(5);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);

        RepublishMessageRecoverer republishMessageRecoverer = new RepublishMessageRecoverer(amqpTemplate, "blackfriar-dle", null);

        RetryOperationsInterceptor retryOperationsInterceptor = stateless()
                .maxAttempts(3)
                .backOffPolicy(backOffPolicy)
                .recoverer(republishMessageRecoverer)
                .build();

        factory.setAdviceChain(retryOperationsInterceptor);

        return factory;
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitHost);
        return connectionFactory;
    }

}