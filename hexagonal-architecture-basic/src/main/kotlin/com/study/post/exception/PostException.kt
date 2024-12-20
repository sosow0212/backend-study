package com.study.post.exception

import com.study.global.CustomException
import com.study.global.CustomExceptionType

class PostException(
    private val postExceptionType: PostExceptionType
) : CustomException(postExceptionType) {

    override fun getExceptionType(): CustomExceptionType {
        return postExceptionType
    }
}
