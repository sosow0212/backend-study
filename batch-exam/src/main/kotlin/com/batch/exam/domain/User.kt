package com.batch.exam.domain

import jakarta.persistence.*

@Entity
@Table(name = "batch_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val email: String,

    var processed: Boolean = false
)
