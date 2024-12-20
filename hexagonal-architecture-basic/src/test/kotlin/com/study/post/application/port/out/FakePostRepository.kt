package com.study.post.application.port.out

import com.study.post.domain.model.Post

class FakePostRepository : PostRepositoryPort {

    private val storage = mutableListOf<Post>()

    override fun findById(id: Long): Post? {
        return storage.find { it.id == id }
    }

    override fun save(post: Post): Post {
        storage.add(post)
        return post
    }

    override fun update(newPost: Post): Post {
        val id = newPost.id!!
        storage[id.toInt()] = newPost
        return newPost
    }
}
