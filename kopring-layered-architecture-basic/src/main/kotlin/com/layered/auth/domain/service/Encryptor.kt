package com.layered.auth.domain.service

interface Encryptor {

    fun encrypt(password: String): String

    fun matches(password: String, encodedPassword: String): Boolean
}
