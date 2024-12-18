package com.study.post.adapter.out

import com.study.post.adapter.out.persistence.entity.PostJpaEntity
import com.study.post.adapter.out.persistence.repository.PostJpaRepository
import com.study.post.application.port.out.PostRepositoryPort
import com.study.post.domain.model.Post
import com.study.post.exception.PostException
import com.study.post.exception.PostExceptionType.POST_NOT_FOUND_EXCEPTION
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryAdapter(
    private val postJpaRepository: PostJpaRepository
) : PostRepositoryPort {

    override fun findById(id: Long): Post? {
        return postJpaRepository.findById(id)
            .map { entity -> Post.of(entity.title, entity.content, entity.id) }
            .orElse(null)
    }

    override fun save(post: Post): Post {
        val entity = PostJpaEntity.of(post.title.value, post.content)
        val savedPost = postJpaRepository.save(entity)
        return Post.of(savedPost.title, savedPost.content, savedPost.id)
    }

    override fun update(id: Long, newPost: Post): Post {
        return postJpaRepository.findById(id)
            .orElseThrow { PostException(POST_NOT_FOUND_EXCEPTION) }
            .apply {
                this.title = newPost.title.value
                this.content = newPost.content
            }
            .let { updatedEntity -> Post.of(updatedEntity.title, updatedEntity.content, updatedEntity.id) }
    }
}
