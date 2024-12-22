package com.layered.auth.application

import com.layered.auth.application.request.SignInRequest
import com.layered.auth.application.request.SignUpRequest
import com.layered.auth.domain.Auth
import com.layered.auth.domain.AuthRepository
import com.layered.auth.domain.service.Encryptor
import com.layered.auth.ui.response.AuthResponse
import com.layered.global.util.throwWhen
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val authRepository: AuthRepository,
    private val encryptor: Encryptor,
    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun signUp(request: SignUpRequest): AuthResponse {
        throwWhen(authRepository.existsByUsername(request.username)) {
            RuntimeException("이미 존재하는 username")
        }

        val savedAuth = authRepository.save(
            Auth.signUpWithEncryption(
                username = request.username,
                password = request.password,
                encryptor = encryptor
            )
        )

        return AuthResponse(tokenProvider.create(savedAuth.id))
    }

    fun signIn(request: SignInRequest): AuthResponse {
        val auth = authRepository.findByUsername(request.username)
            ?: throw RuntimeException("존재하지 않는 username")

        require(auth.matches(request.password, encryptor)) { "비밀번호가 일치하지 않습니다" }

        return AuthResponse(tokenProvider.create(auth.id))
    }
}
