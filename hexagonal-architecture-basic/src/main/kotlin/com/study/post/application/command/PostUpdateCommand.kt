package com.study.post.application.command

data class PostUpdateCommand(
    val title: String,
    val content: String,
) {
}
