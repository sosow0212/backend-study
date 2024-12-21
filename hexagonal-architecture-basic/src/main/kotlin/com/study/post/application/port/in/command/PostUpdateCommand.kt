package com.study.post.application.port.`in`.command

data class PostUpdateCommand(
    val title: String,
    val content: String,
) {
}
