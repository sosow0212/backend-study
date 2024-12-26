package com.layered.helper

import com.layered.auth.application.AuthService
import com.layered.auth.application.TokenProvider
import com.layered.global.config.auth.support.AuthenticationContext
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.context.bean.override.mockito.MockitoBean

class MockBeanInjection() {

    @MockitoBean
    protected lateinit var jpaMetamodelMappingContext: JpaMetamodelMappingContext

    @MockitoBean
    protected lateinit var tokenProvider: TokenProvider

    @MockitoBean
    protected lateinit var authenticationContext: AuthenticationContext

    @MockitoBean
    protected lateinit var authService: AuthService
}
