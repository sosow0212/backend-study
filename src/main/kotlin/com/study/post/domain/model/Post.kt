package com.study.post.domain.model

class Post(
    var title: String,
    var content: String,
    var id: Long? = 0L,
) {

    fun update(newPost: Post) {
        this.title = newPost.title
        this.content = newPost.content
    }
}
