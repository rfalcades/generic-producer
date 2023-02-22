package com.bees.integration.genericproducer

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStream

@Service
class Worker(private val rabbitProducer : RabbitProducer,
             private val appProperties: AppProperties) {

    private val logger = KotlinLogging.logger {}
    private val allowedEntities: List<String>
        get() = appProperties.allowedEntities.split(',').toList()

    fun send(topic: String, content: String) {
        rabbitProducer.publish(topic, content)
    }

    fun getBodyContent(body:InputStream) : String {
        return body.bufferedReader().use(BufferedReader::readText)
    }

    fun getEntityName(pathUrl:String) : String {

        if (pathUrl.isNullOrBlank())
            return ""

        val parts = pathUrl.split("/")

        if (parts.size > 1)
            if (allowedEntities.contains(parts[1].uppercase()))
                return parts[1]

        logger.info("Entity not found! Path: $pathUrl")
        return ""
    }
}