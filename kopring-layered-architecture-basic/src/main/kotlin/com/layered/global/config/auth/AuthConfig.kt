package com.layered.global.config.auth

import com.layered.global.config.auth.interceptor.LoginValidCheckerInterceptor
import com.layered.global.config.auth.interceptor.ParseMemberIdFromTokenInterceptor
import com.layered.global.config.auth.interceptor.PathMatcherInterceptor
import com.layered.global.config.auth.support.HttpMethod.DELETE
import com.layered.global.config.auth.support.HttpMethod.GET
import com.layered.global.config.auth.support.HttpMethod.OPTIONS
import com.layered.global.config.auth.support.HttpMethod.PATCH
import com.layered.global.config.auth.support.HttpMethod.POST
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthConfig(
    private val parseMemberIdFromTokenInterceptor: ParseMemberIdFromTokenInterceptor,
    private val loginValidCheckerInterceptor: LoginValidCheckerInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(parseMemberIdFromTokenInterceptor())
        registry.addInterceptor(loginValidCheckerInterceptor())
    }

    private fun parseMemberIdFromTokenInterceptor(): HandlerInterceptor {
        return PathMatcherInterceptor(parseMemberIdFromTokenInterceptor)
            .excludePathPattern("/**", OPTIONS)
    }

    private fun loginValidCheckerInterceptor(): HandlerInterceptor {
        return PathMatcherInterceptor(loginValidCheckerInterceptor)
            .excludePathPattern("/**", OPTIONS)
            .excludePathPattern("/auth", POST, GET)
            .addPathPatterns("/auth/test", GET, POST, PATCH, DELETE)
    }
}
