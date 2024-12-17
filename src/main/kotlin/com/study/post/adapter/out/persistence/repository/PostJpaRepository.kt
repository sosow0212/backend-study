package com.study.post.adapter.out.persistence.repository

import com.study.post.adapter.out.persistence.entity.PostJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostJpaEntity, Long> {
}
