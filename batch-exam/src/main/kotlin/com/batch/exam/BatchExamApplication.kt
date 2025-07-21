package com.batch.exam

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchExamApplication

fun main(args: Array<String>) {
	runApplication<BatchExamApplication>(*args)
}
