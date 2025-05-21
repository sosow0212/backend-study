package com.kafka.producer.controller

import com.kafka.producer.service.KafkaProducerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/kafka")
class KafkaProducerController(
    private val kafkaProducerService: KafkaProducerService
) {
    
    @PostMapping("/publish")
    fun publishMessage(
        @RequestParam topic: String,
        @RequestParam(required = false, defaultValue = "") key: String,
        @RequestBody message: String
    ): ResponseEntity<String> {
        kafkaProducerService.sendMessage(topic, key, message)
        return ResponseEntity.ok("Message sent to topic: $topic")
    }
    
    @GetMapping("/send")
    fun sendSimpleMessage(
        @RequestParam(defaultValue = "example-topic") topic: String,
        @RequestParam message: String
    ): ResponseEntity<String> {
        kafkaProducerService.sendMessage(topic, message)
        return ResponseEntity.ok("Message sent to topic: $topic")
    }
}
