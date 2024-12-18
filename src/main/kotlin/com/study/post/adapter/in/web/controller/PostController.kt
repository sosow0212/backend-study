package com.study.post.adapter.`in`.web.controller

import com.study.post.adapter.`in`.web.controller.request.PostCreateRequest
import com.study.post.adapter.`in`.web.controller.request.PostUpdateRequest
import com.study.post.application.port.`in`.PostUseCase
import com.study.post.application.port.out.response.PostResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/post")
@RestController
class PostController(
    private var postUseCase: PostUseCase
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val postResponse = postUseCase.findById(id)
        return ResponseEntity.ok(postResponse)
    }

    @PostMapping
    fun save(@RequestBody @Valid postCreateRequest: PostCreateRequest): ResponseEntity<PostResponse> {
        val postResponse = postUseCase.create(postCreateRequest.toCommand())
        return ResponseEntity.created(URI.create("/post/${postResponse.id}"))
            .body(postResponse)
    }

    @PatchMapping("/{id}")
    fun patchPost(
        @PathVariable id: Long,
        @RequestBody @Valid postUpdateRequest: PostUpdateRequest
    ): ResponseEntity<PostResponse> {
        val updatedPostPost = postUseCase.update(id, postUpdateRequest.toCommand())
        return ResponseEntity.ok(updatedPostPost)
    }
}
