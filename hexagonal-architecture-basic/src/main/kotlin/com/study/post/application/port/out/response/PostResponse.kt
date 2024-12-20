package com.study.post.application.port.out.response

import com.study.post.domain.model.Post

data class PostResponse(
    val id: Long? = 0L,
    val title: String,
    val content: String
) {

    companion object {

        fun from(post: Post) = PostResponse(
            id = post.id,
            title = post.title.value,
            content = post.content
        )
    }
}
