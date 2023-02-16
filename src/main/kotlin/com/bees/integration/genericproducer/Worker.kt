package com.bees.integration.genericproducer

import org.springframework.stereotype.Service

@Service
class Worker(rabbitProducer : RabbitProducer) {

    val rabbitProducer = rabbitProducer

    fun send(topic: String, version: String) {

        rabbitProducer.publish(topic)

    }

}