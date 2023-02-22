package com.bees.integration.genericproducer

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.ConnectionFactory
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class RabbitProducer {

    private val logger = KotlinLogging.logger {}

    fun publish(topicName:String, message:String) {

        var exchange = topicName

        val factory = ConnectionFactory()
        // factory.host = "localhost"
        val connection = factory.newConnection()
        val channel = connection?.createChannel()

        channel?.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC)
        channel?.basicPublish(exchange, "", null, message.toByteArray())

        connection?.close()
    }
}