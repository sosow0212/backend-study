package com.study.post.domain.model

import com.study.post.domain.model.vo.Title

class Post private constructor(
    var title: Title,
    var content: String,
    var id: Long? = 0L
) {

    fun update(newPost: Post) {
        this.title = newPost.title
        this.content = newPost.content
    }

    companion object {
        fun of(title: String, content: String, id: Long? = 0L): Post = Post(Title.from(title), content, id)
    }
}
