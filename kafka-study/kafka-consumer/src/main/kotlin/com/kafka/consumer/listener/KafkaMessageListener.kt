package com.kafka.consumer.listener

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaMessageListener {

    private val logger = LoggerFactory.getLogger(KafkaMessageListener::class.java)

    @KafkaListener(topics = ["example-topic"], groupId = "kafka-study-group")
    fun listen(
        @Payload message: String,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String,
        @Header("kafka_receivedPartitionId") partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        acknowledgment: Acknowledgment
    ) {
        try {
            logger.info(
                "[수신 완료] 메시지 : {} 토픽 : {}, 파티션 : {}, 오프셋: {}",
                message,
                topic,
                partition,
                offset
            )
            acknowledgment.acknowledge()
            logger.info("메시지 수신 성공!")
        } catch (e: Exception) {
            logger.error("메시지 수신 실패! message: {}", e.message, e)
            acknowledgment.acknowledge()
        }
    }
}
