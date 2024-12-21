package com.study.post.application.port.`in`.command

data class PostCreateCommand(
    var title: String,
    var content: String,
) {
}
