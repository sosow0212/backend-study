package com.layered.auth.infrastructure.persistence

import com.layered.auth.domain.Auth
import com.layered.auth.domain.AuthRepository
import org.springframework.stereotype.Repository

@Repository
class AuthRepositoryImpl(
    private val authJpaRepository: AuthJpaRepository
) : AuthRepository {

    override fun save(auth: Auth): Auth {
        return authJpaRepository.save(auth)
    }

    override fun findByUsername(username: String): Auth? {
        return authJpaRepository.findByUsername(username)
    }

    override fun existsByUsername(username: String): Boolean {
        return authJpaRepository.existsAuthByUsername(username)
    }
}
