package com.study.post.adapter.out

import com.study.post.adapter.out.persistence.entity.PostJpaEntity
import com.study.post.adapter.out.persistence.repository.PostJpaRepository
import com.study.post.application.port.out.PostRepositoryPort
import com.study.post.domain.model.Post
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryAdapter(
    private val postJpaRepository: PostJpaRepository
) : PostRepositoryPort {

    override fun findById(id: Long): Post? {
        return postJpaRepository.findById(id)
            .map { entity -> Post(entity.title, entity.content, entity.id) }
            .orElse(null)
    }

    override fun save(post: Post): Post {
        val entity = PostJpaEntity.of(post.title, post.content)
        val savedPost = postJpaRepository.save(entity)
        return Post(savedPost.title, savedPost.content, savedPost.id)
    }
}
