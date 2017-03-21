package com.blackfriar.gradle

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

import java.time.Instant
import java.time.ZoneOffset

class SendEvent extends AbstractMessageProducerTask {

    @Input
    def  exchangeName = "blackfriar"

    @Input
    def routingKey = "FOO.BAR"


    @TaskAction
    def sendEvent() {
        Instant now = Instant.now()

        def time = Date.from(now).format("yyyy-MM-dd'T'HH:mm:ss.SSSX", TimeZone.getTimeZone(ZoneOffset.UTC.id))

        def binding = [ someProp: "value",
                        created: time]

        def message = engine.createTemplate(messageFile.text).make(binding).toString()

        amqpProducer().sendJsonMessage(message, routingKey)

        amqpProducer().closeAll()
    }
}
