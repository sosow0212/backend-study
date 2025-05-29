package com.example.sync.account.domain

data class Account(
    var balance: Long,
    val updateMilli: Long,
    val updateNano: Long
) {

    fun deposit(amount: Long) {
        validateDepositAmount(amount)
        this.balance += amount
    }

    private fun validateDepositAmount(amount: Long) {
        require(amount >= MINIMUM_DEPOSIT_AMOUNT) { "입금 가능액은 최소 1원입니다." }
    }

    fun withdraw(amount: Long) {
        validateWithdrawBalance(
            myBalance =  balance,
            withdrawAmount = amount
        )
        this.balance -= amount
    }

    private fun validateWithdrawBalance(myBalance: Long, withdrawAmount: Long) {
        if (withdrawAmount < MINIMUM_WITHDRAW_AMOUNT) {
            throw IllegalArgumentException("출금할 금액은 1원 이상이어야 합니다.")
        }

        if (myBalance < withdrawAmount) {
            throw IllegalArgumentException("출금 금액이 잔고 금액보다 큽니다.")
        }
    }

    companion object {
        private const val MINIMUM_DEPOSIT_AMOUNT = 1L
        private const val MINIMUM_WITHDRAW_AMOUNT = 1L
        private const val DEFAULT_ACCOUNT_BALANCE = 0L

        fun createDefault(): Account {
            return Account(
                balance = DEFAULT_ACCOUNT_BALANCE,
                updateMilli = System.currentTimeMillis(),
                updateNano = System.nanoTime()
            )
        }
    }
}
