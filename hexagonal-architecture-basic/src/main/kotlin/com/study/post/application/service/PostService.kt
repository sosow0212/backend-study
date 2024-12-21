package com.study.post.application.service

import com.study.post.adapter.out.persistence.entity.mapper.PostMapper
import com.study.post.application.port.`in`.command.PostCreateCommand
import com.study.post.application.port.`in`.command.PostUpdateCommand
import com.study.post.application.port.`in`.PostUseCase
import com.study.post.application.port.out.PostRepositoryPort
import com.study.post.application.port.out.response.PostResponse
import com.study.post.domain.model.Post
import com.study.post.exception.PostException
import com.study.post.exception.PostExceptionType.POST_NOT_FOUND_EXCEPTION
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepositoryPort: PostRepositoryPort,
) : PostUseCase {

    @Transactional
    override fun create(postCreateCommand: PostCreateCommand): PostResponse {
        val post = PostMapper.commandToPost(postCreateCommand)
        val savedPost = postRepositoryPort.save(post)
        return PostMapper.domainToResponse(savedPost);
    }

    @Transactional
    override fun update(
        id: Long,
        postUpdateCommand: PostUpdateCommand
    ): PostResponse {
        val post = findPostById(id)
        post.update(PostMapper.commandToPost(postUpdateCommand))
        postRepositoryPort.update(post)
        return PostMapper.domainToResponse(post)
    }

    private fun findPostById(id: Long): Post {
        return postRepositoryPort.findById(id)
            ?: throw PostException(POST_NOT_FOUND_EXCEPTION)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): PostResponse {
        val post = findPostById(id)
        return PostMapper.domainToResponse(post)
    }
}
