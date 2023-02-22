package com.bees.integration.genericproducer

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class MessageInterceptor(val worker: Worker) : HandlerInterceptor {

    private val logger = KotlinLogging.logger {}

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (request == null || request.requestURI.isNullOrEmpty())
            return false

        if (request.method == "POST") {

            val content = worker.getBodyContent(request.inputStream)
            val entity = worker.getEntityName(request.requestURI)

            if (entity.isNotBlank() && content.isNotBlank()) {
                worker.send(entity, content)
                logger.info("Sent to $entity")
            } else {
                logger.info("Entity or message is empty!")
            }
        } else {
            println("Method not mapped: ${request.method}")
        }

        return false
    }

}
