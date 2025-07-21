package com.batch.exam

import com.batch.exam.domain.User
import com.batch.exam.domain.UserRepository
import com.batch.exam.service.JobService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchExamApplication(
    private val jobService: JobService,
    private val userRepository: UserRepository,
) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(BatchExamApplication::class.java)

    override fun run(vararg args: String?) {
        initDB()

        logger.info("배치 스타트!")
        jobService.run()
        logger.info("배치 끝!")
    }

    fun initDB() {
        // 20
        val users = ArrayList<User>()

        for (i in 1..30) {
            users.add(User(name = "홍길동", email = "hong@example.com"))
        }

        userRepository.saveAll(users)

        for (i in 1..30) {
            users.add(User(name = "홍길동", email = "hong@example.com"))
        }

        userRepository.saveAll(users)
    }
}

fun main(args: Array<String>) {
    runApplication<BatchExamApplication>(*args)
}
