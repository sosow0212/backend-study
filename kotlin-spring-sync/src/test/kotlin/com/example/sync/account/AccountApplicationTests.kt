package com.example.sync.account

import com.example.sync.account.ui.BalanceController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
class AccountApplicationTests {
    @Autowired
    lateinit var controller: BalanceController
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @Test
    fun contextLoads() {
        // given
        log.info("calling get balance")
        val account = controller.balance(1)

        // when & then
        assertEquals(account.balance, 0)
        assertEquals(10000, controller.deposit(1, 10000).balance)
        assertEquals(5000, controller.withdraw(1, 5000).balance)
    }

    @Test
    fun `동시 입금 요청이 여러 건일 때 하나만 성공해야 한다`() {
        // given
        val accountId = 100L
        val initial = controller.balance(accountId)
        assertEquals(0, initial.balance)

        // when
        val threadCount = 10
        val latch = CountDownLatch(threadCount)
        val executor = Executors.newFixedThreadPool(threadCount)
        val results = mutableListOf<Long>()

        repeat(threadCount) {
            executor.submit {
                try {
                    val result = controller.deposit(accountId, 1000L)
                        .balance
                    synchronized(results) { results.add(result) }
                } catch (e: Exception) {
                    log.info("입금 실패: ${e.message}")
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await(5, TimeUnit.SECONDS)

        // then
        val finalAccount = controller.balance(accountId)
        assertEquals(1000L, finalAccount.balance)
        assertEquals(1, results.count { it == 1000L })
    }

    @Test
    fun `입금과 출금이 동시에 올 때 순차적으로 처리되어야 한다`() {
        // given
        val accountId = 200L
        controller.deposit(accountId, 10000L)

        // when
        val latch = CountDownLatch(2)
        val executor = Executors.newFixedThreadPool(2)

        executor.submit {
            try {
                controller.deposit(accountId, 5000L)
            } finally {
                latch.countDown()
            }
        }
        executor.submit {
            try {
                controller.withdraw(accountId, 3000L)
            } finally {
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)

        // then
        val finalBalance = controller.balance(accountId).balance
        assertEquals(12000L, finalBalance)
    }
}
