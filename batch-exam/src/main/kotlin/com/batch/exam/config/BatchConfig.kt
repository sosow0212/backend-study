// config/BatchConfig.kt
package com.batch.exam.config

import com.batch.exam.domain.User
import com.batch.exam.domain.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.data.RepositoryItemWriter
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.transaction.PlatformTransactionManager
import java.util.concurrent.atomic.AtomicInteger

@Configuration
class BatchConfig(
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) {

    private val logger = LoggerFactory.getLogger(BatchConfig::class.java)
    private val processedCount = AtomicInteger(0)  // 처리된 아이템 카운터

    companion object {
        const val CHUNK_SIZE = 10
    }

    @Bean
    fun userProcessJob(): Job {
        return JobBuilder("userProcessJob", jobRepository)
            .start(userProcessStep())
            .build()
    }

    @Bean
    fun userProcessStep(): Step {
        return StepBuilder("userProcessStep", jobRepository)
            .chunk<User, User>(CHUNK_SIZE, transactionManager)
            .reader(userReader())
            .processor(userProcessor())
            .writer(userWriter())
            .build()
    }

    @Bean
    @StepScope
    fun userReader(): RepositoryItemReader<User> {
        return RepositoryItemReaderBuilder<User>()
            .name("userReader")
            .repository(userRepository)
            .methodName("findByProcessedFalse")
            .pageSize(CHUNK_SIZE)
            .saveState(true) // 상태 저장
            .sorts(mapOf("id" to Sort.Direction.ASC))
            .build()
    }

    @Bean
    fun userProcessor(): ItemProcessor<User, User> {
        return ItemProcessor { user ->
            val currentCount = processedCount.incrementAndGet()
            logger.info("Processing user #$currentCount: ${user.name}")

            // 세 번째 청크의 첫 번째 아이템(21번째)에서 실패
            println("currentCount = $currentCount")
            if (currentCount == 21) {
                logger.error("의도적인 실패! 세 번째 청크 처리 중 오류 발생")
                throw RuntimeException("세 번째 청크에서 의도적으로 발생시킨 오류")
            }

            // 간단한 처리 로직 (이메일을 대문자로 변환)
            user.copy(
                email = user.email.uppercase(),
                processed = true
            )
        }
    }

    @Bean
    fun userWriter(): RepositoryItemWriter<User> {
        return RepositoryItemWriterBuilder<User>()
            .repository(userRepository)
            .methodName("save")
            .build()
    }
}
