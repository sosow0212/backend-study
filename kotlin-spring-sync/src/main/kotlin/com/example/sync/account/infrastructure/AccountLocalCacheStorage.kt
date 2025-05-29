package com.example.sync.account.infrastructure

import com.example.sync.account.application.AccountCacheStorage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock

@Component
class AccountLocalCacheStorage : AccountCacheStorage {

    private val locks: ConcurrentHashMap<String, ReentrantLock> = ConcurrentHashMap()

    override fun tryLock(lockKey: String): Boolean {
        val lock = locks.computeIfAbsent(lockKey) { ReentrantLock() }
        return lock.tryLock()
    }

    override fun hasLock(lockKey: String): Boolean {
        return locks[lockKey]
            ?.isHeldByCurrentThread
            ?: false
    }

    override fun releaseLock(lockKey: String): Boolean {
        val lock = locks[lockKey]
            ?: return false

        return if (lock.isHeldByCurrentThread) {
            lock.unlock()
            true
        } else {
            false
        }
    }
}
