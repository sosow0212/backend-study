package com.hexagonal.view

interface InputView {

    fun requestCarNames(): List<String>

    fun requestTryCount(): Int
}
