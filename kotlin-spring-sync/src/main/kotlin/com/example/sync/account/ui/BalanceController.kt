package com.example.sync.account.ui

import com.example.sync.account.domain.Account
import com.example.sync.account.application.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class BalanceController(
    private val accountService: AccountService
) {

    @GetMapping("{id}/balance")
    fun balance(@PathVariable id: Long): Account {
        return accountService.findBalanceById(id)
    }

    @PostMapping("{id}/deposit")
    fun deposit(@PathVariable id: Long, @RequestBody amount: Long): Account {
        return accountService.depositAmountFromAccountId(id, amount)
    }

    @PostMapping("{id}/withdraw")
    fun withdraw(@PathVariable id: Long, @RequestBody amount: Long): Account {
        return accountService.withdrawAmountFromAccountId(id, amount)
    }
}
