package com.blackfriar.gradle

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultSaslConfig

import javax.net.ssl.SSLContext

class AmqpProducer {

    Connection connection
    Channel channel
    String exchangeName

    AmqpProducer(String keystoreLocation, String keystorePassword, String trustStoreLocation, String trustStorePassword, String host, int port, String exchangeName, String exchangeType) {
//        System.setProperty("javax.net.ssl.keyStore", keystoreLocation)
//        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12")
//        System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword)
//        System.setProperty("javax.net.ssl.trustStore", trustStoreLocation)
//        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword)
//        System.setProperty("https.protocol", "TLSv1")

//        def context = SSLContext.default

        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost(host)
        factory.setPort(port)
        // for cert auth
        // factory.useSslProtocol(context)
        // factory.setSaslConfig(DefaultSaslConfig.EXTERNAL)

        // for password auth
        factory.setUsername("guest")
        factory.setPassword("guest")
        factory.setSaslConfig(DefaultSaslConfig.PLAIN)

        this.connection = factory.newConnection()
        this.channel = connection.createChannel()
        this.channel.exchangeDeclare(exchangeName, exchangeType, true)
        this.exchangeName = exchangeName
    }

    def sendXmlMessage(String message, String routingKey, Map<String, Object> headers) {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
            .contentType("application/xml")
            .headers(headers)
            .build()

        sendMessage(message, routingKey, properties)

    }

    def sendMessage(String message, String routingKey, AMQP.BasicProperties props) {
        println message
        channel.basicPublish(exchangeName, routingKey, props, message.getBytes("UTF-8"))
    }

    def sendJsonMessage(String message, String routingKey) {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
            .contentType("application/json")
            .build()

        sendMessage(message, routingKey, properties)
    }

    def closeAll() {
        channel.close()
        connection.close()
    }
}
