package com.study.global

import org.springframework.http.HttpStatusCode

interface CustomExceptionType {

    val subject: String
    val message: String
    val httpStatusCode: HttpStatusCode
}
