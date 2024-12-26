package com.layered.global.exceptions

abstract class CustomException(
    customExceptionType: CustomExceptionType
) : RuntimeException("[${customExceptionType.subject}]: ${customExceptionType.message}") {

    abstract fun getExceptionType(): CustomExceptionType
}
