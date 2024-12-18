package com.study.post.application.port.out

import com.study.post.domain.model.Post

interface PostRepositoryPort {

    fun findById(id: Long): Post?

    fun save(post: Post): Post

    fun update(id: Long, newPost: Post): Post
}
