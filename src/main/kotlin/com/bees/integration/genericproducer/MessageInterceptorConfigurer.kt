package com.bees.integration.genericproducer

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class MessageInterceptorConfigurer(messageInterceptor : MessageInterceptor) : WebMvcConfigurer {

    var messageInterceptor = messageInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(messageInterceptor)
    }

}