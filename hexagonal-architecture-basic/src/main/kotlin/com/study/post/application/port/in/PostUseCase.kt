package com.study.post.application.port.`in`

import com.study.post.application.command.PostCreateCommand
import com.study.post.application.command.PostUpdateCommand
import com.study.post.application.port.out.response.PostResponse

interface PostUseCase {

    fun create(postCreateCommand: PostCreateCommand): PostResponse

    fun update(
        id: Long,
        postUpdateCommand: PostUpdateCommand
    ): PostResponse

    fun findById(id: Long): PostResponse
}
