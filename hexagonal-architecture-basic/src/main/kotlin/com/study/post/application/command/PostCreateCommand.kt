package com.study.post.application.command

data class PostCreateCommand(
    var title: String,
    var content: String,
) {
}
