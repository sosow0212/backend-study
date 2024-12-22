package com.layered.auth.ui

import com.layered.auth.application.AuthService
import com.layered.auth.application.request.SignInRequest
import com.layered.auth.application.request.SignUpRequest
import com.layered.auth.ui.response.AuthResponse
import com.layered.global.annotation.AuthMember
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) : AuthApi {

    @PostMapping("/sign-up")
    override fun signUp(request: SignUpRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.signUp(request))
    }

    @GetMapping("/sign-in")
    override fun signIn(request: SignInRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.signIn(request))
    }

    @Operation(summary = "test")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/test")
    fun test(@AuthMember id: Long): String {
        return "hi $id"
    }
}
