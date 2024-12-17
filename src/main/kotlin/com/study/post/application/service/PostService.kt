package com.study.post.application.service

import com.study.post.adapter.out.persistence.entity.mapper.PostMapper
import com.study.post.application.command.PostCreateCommand
import com.study.post.application.command.PostUpdateCommand
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
        /**
         *  TODO : 더티체킹 안됨 -> 도메인을 변환해서 적용하니 당연함.. Entity까지 닿지 않음 어떻게 해야 더 좋은 구조일지?
         *  쿼리를 두 번 날려야하나? repo에서 하기에는 비즈니스 로직(orElse..)의 책임을 따져보자
         */

        val post = findPostById(id)
        val newPost = PostMapper.commandToPost(postUpdateCommand)
        post.update(newPost)
        return PostMapper.domainToResponse(post)
    }

    private fun findPostById(id: Long): Post {
        val post = postRepositoryPort.findById(id)
            ?: throw PostException(POST_NOT_FOUND_EXCEPTION)
        return post
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): PostResponse {
        val post = findPostById(id)
        return PostMapper.domainToResponse(post)
    }
}
