package com.blackfriar.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by paulwatson on 24/07/2016.
 */
@Component
public class HelloMessageListener {

    Logger log = LoggerFactory.getLogger(HelloMessageListener.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hello", durable = "true"),
            exchange = @Exchange(value = "blackfriar-ex"), key = "*"),
            containerFactory = "blackfriarContainerFactory"

    )
    public void processHelloMessage(@Payload byte [] payload){
        log.info("Message: {}", new String(payload));
    }

}

