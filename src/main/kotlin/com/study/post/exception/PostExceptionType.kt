package com.study.post.exception

import com.study.global.CustomExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class PostExceptionType : CustomExceptionType {

    POST_NOT_FOUND_EXCEPTION {
        override val subject: String = "POST_EXCEPTION"
        override val message: String = "게시글을 찾을 수 없습니다."
        override val httpStatusCode: HttpStatusCode = HttpStatus.NOT_FOUND
    },
}
