package com.bees.integration.genericproducer

import com.rabbitmq.client.ConnectionFactory
import org.springframework.stereotype.Component

@Component
class RabbitProducer {

    fun publish(topicName:String) {

        var exchange = topicName //"rabbit.kotlin"
        val exchangeType = "topic" //"queue"
        val routingKey = "a.b"
        val emptyQueueName = ""
        val durable = false
        val exclusive = true
        val autodelete = true
        val autoAck = true
        val basicProperties = null
        val arguments = null

        val conx = ConnectionFactory()

        val connection = conx.newConnection()
        val channel = connection?.createChannel()

        val declareOk = when (exchangeType) {
            "topic" -> channel?.exchangeDeclare(exchange, exchangeType, false)
            "queue" -> channel?.queueDeclare(exchange, true, false, false, arguments)
            else -> null
        }

        channel?.basicPublish(exchange, routingKey, basicProperties, "hello".toByteArray())

        println("published")

        connection?.close()
    }
}