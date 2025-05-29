package com.example.sync.account.domain

interface AccountRepository {

    fun updateAccount(id: Long, account: Account): Account
    fun findAccountOrDefault(id: Long, account: Account): Account
}
