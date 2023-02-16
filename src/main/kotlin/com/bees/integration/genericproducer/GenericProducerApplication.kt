package com.bees.integration.genericproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenericProducerApplication

fun main(args: Array<String>) {
	runApplication<GenericProducerApplication>(*args)
}

