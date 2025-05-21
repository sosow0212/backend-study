package com.kafka.producer.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)

    fun sendMessage(
        topic: String,
        key: String,
        message: String
    ): ListenableFuture<SendResult<String, String>> {
        logger.info("토픽 : {}, 키: {}, 메시지 : {}", topic, key, message)

        val future = kafkaTemplate.send(topic, key, message)

        future.addCallback(object : ListenableFutureCallback<SendResult<String, String>> {
            override fun onSuccess(result: SendResult<String, String>?) {
                if (result != null) {
                    logger.info(
                        "성공! 토픽: {}, 파티션: {}, 오프셋: {}",
                        result.recordMetadata.topic(),
                        result.recordMetadata.partition(),
                        result.recordMetadata.offset()
                    )
                }
            }

            override fun onFailure(ex: Throwable) {
                logger.error("전송 실패! 토픽: {}", topic, ex)
            }
        })

        return future
    }

    fun sendMessage(topic: String, message: String): ListenableFuture<SendResult<String, String>> {
        return sendMessage(topic, "", message)
    }
}
