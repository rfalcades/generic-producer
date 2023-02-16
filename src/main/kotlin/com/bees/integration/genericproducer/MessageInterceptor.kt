package com.bees.integration.genericproducer

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.io.BufferedReader
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class MessageInterceptor(worker:Worker) : HandlerInterceptor {

    val worker = worker

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        println("Pre handle was calling: ${request.requestURL} ${request.method} ${request.pathInfo} ${request.contextPath} ${request.requestURI}")

        if (request.method == "POST") {
            val content = request.inputStream.bufferedReader().use(BufferedReader::readText)
            println("Body content: $content")

            val topic = request.requestURI.split("/")[1]

            worker.send(topic, "")

        } else {
            println("Method not mapped: ${request.method}")
        }

        return false
    }

}
