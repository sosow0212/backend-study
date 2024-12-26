package com.layered.auth.ui

import com.api.helper.IntegrationHelper
import com.layered.auth.application.request.SignInRequest
import com.layered.auth.application.request.SignUpRequest
import com.layered.auth.domain.Auth
import com.layered.auth.domain.AuthRepository
import com.layered.auth.domain.service.Encryptor
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthControllerTest(
    private val authRepository: AuthRepository,
    private val encryptor: Encryptor
) : IntegrationHelper(){

    @Test
    fun `회원가입을 진행한다`() {
        // given
        val request = SignUpRequest("username", "password")

        // when
        val response = RestAssured.given().log().all()
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .post("/auth/sign-up")
            .then().log().all()
            .extract()

        // then
        assertEquals(response.statusCode(), HttpStatus.CREATED.value())
    }

    @Test
    fun `로그인을 진행한다`() {
        // given
        authRepository.save(Auth.signUpWithEncryption(username = "username", password = "password", encryptor = encryptor))
        val request = SignInRequest("username", "password")

        // when
        val response = RestAssured.given().log().all()
            .`when`()
            .contentType(ContentType.JSON)
            .body(request)
            .get("/auth/sign-in")
            .then().log().all()
            .extract()

        // then
        assertEquals(response.statusCode(), HttpStatus.OK.value())
    }
}
