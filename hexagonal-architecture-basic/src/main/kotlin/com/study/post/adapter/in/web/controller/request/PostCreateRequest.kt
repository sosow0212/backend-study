package com.study.post.adapter.`in`.web.controller.request

import com.study.post.application.command.PostCreateCommand
import jakarta.validation.constraints.NotBlank

data class PostCreateRequest(
    @field:NotBlank(message = "title이 비면 안됩니다.")
    val title: String,

    @field:NotBlank(message = "content가 비면 안됩니다.")
    val content: String,
) {

    fun toCommand(): PostCreateCommand {
        return PostCreateCommand(title = title, content = content)
    }
}
