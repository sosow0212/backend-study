package com.example.sync.account.infrastructure

import com.example.sync.account.domain.Account
import com.example.sync.account.domain.AccountRepository
import org.springframework.stereotype.Component
import java.lang.Math.random

/**
 * 이 클래스는 변경하지 않습니다.
 */
@Component
class AccountLocalDatabase : AccountRepository {

    private val db: HashMap<Long, Account> = HashMap()

    override fun updateAccount(id: Long, account: Account): Account {
        // update
        Thread.sleep(random().toLong() * 300L + 100)

        val account = Account(
            balance = account.balance,
            updateMilli = System.currentTimeMillis(),
            updateNano = System.nanoTime()
        )

        db[id] = account
        return account
    }

    override fun findAccountOrDefault(id: Long, account: Account): Account {
        Thread.sleep(random().toLong() * 100L + 100)

        if (!db.containsKey(id)) {
            db[id] = account
        }

        return db[id]!!
    }
}
