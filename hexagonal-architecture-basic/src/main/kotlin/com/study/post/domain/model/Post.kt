package com.study.post.domain.model

import com.study.post.domain.model.vo.Title

class Post private constructor(
    var title: Title,
    var content: String,
    var id: Long? = DEFAULT_ID
) {

    fun update(newPost: Post) {
        this.title = newPost.title
        this.content = newPost.content
    }

    companion object {
        private const val DEFAULT_ID = 0L

        fun of(title: String, content: String, id: Long? = DEFAULT_ID): Post = Post(Title.from(title), content, id)
    }
}
