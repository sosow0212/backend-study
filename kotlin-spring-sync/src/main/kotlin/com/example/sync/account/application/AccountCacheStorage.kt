package com.example.sync.account.application

interface AccountCacheStorage {

    fun tryLock(lockKey: String): Boolean

    fun hasLock(lockKey: String): Boolean

    fun releaseLock(lockKey: String): Boolean
}
