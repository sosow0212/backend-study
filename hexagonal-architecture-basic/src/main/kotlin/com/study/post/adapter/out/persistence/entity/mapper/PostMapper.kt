package com.study.post.adapter.out.persistence.entity.mapper

import com.study.post.application.command.PostCreateCommand
import com.study.post.application.command.PostUpdateCommand
import com.study.post.application.port.out.response.PostResponse
import com.study.post.domain.model.Post

data class PostMapper(
    val title: String,
    val content: String,
    val id: Long,
) {
    companion object {
        fun commandToPost(command: PostCreateCommand): Post = Post.of(command.title, command.content)

        fun commandToPost(command: PostUpdateCommand): Post = Post.of(command.title, command.content)

        fun domainToResponse(post: Post): PostResponse = PostResponse.from(post)
    }
}
