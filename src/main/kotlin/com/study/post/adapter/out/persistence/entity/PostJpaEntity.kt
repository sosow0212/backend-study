package com.study.post.adapter.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class PostJpaEntity(
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
) {

    companion object {
        fun of(title: String, content: String) = PostJpaEntity(title, content)
    }
}
