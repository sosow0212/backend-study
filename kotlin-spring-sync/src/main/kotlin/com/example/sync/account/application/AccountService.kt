package com.example.sync.account.application

import com.example.sync.account.domain.Account
import com.example.sync.account.domain.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val repository: AccountRepository,
    private val cacheStorage: AccountCacheStorage
) {

    // 잔고 내역 조회
    fun findBalanceById(id: Long) = findAccountOrDefault(id)

    // 입금
    // 제약 1. 입금과 출금이 동시 오는 경우 차례대로 실행
    // 제약 2. 입금 요청이 여러 건인 경우 최초 한 건만 성공
    fun depositAmountFromAccountId(id: Long, amount: Long): Account {
        val foundAccount = findAccountOrDefault(id)

        val depositLockKey = makeLockKey(DEPOSIT_LOCK_PREFIX, id)
        check(cacheStorage.tryLock(depositLockKey)) {
            "accountId: ${id}에 이미 입금 요청이 진행 중입니다."
        }

        foundAccount.deposit(amount)
        repository.updateAccount(id, foundAccount)
        cacheStorage.releaseLock(depositLockKey)
        return foundAccount
    }

    // 출금
    // 제약1. 동시에 출금 요청이 오는 경우 차례대로 실행
    // 제약2. 입금과 출금이 동시 오는 경우 차례대로 실행
    fun withdrawAmountFromAccountId(id: Long, amount: Long): Account {
        val foundAccount = findAccountOrDefault(id)
        val lockKey = makeLockKey(WITHDRAW_LOCK_PREFIX, id)

        while (!cacheStorage.hasLock(lockKey)) {
            cacheStorage.hasLock(lockKey)
            foundAccount.withdraw(amount)
            cacheStorage.releaseLock(lockKey)
            break
        }

        return foundAccount
    }

    private fun findAccountOrDefault(id: Long) = repository.findAccountOrDefault(id, Account.createDefault())

    private fun makeLockKey(lockPrefix: String, accountId: Long) = "${lockPrefix}${accountId}"

    companion object {
        private const val DEPOSIT_LOCK_PREFIX = "account_deposit_"
        private const val WITHDRAW_LOCK_PREFIX = "account_withdraw_"
    }
}
