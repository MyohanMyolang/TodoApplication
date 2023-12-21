package com.sparta.todoapp.auth

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {
    @Bean
    fun authConfig1(request: HttpServletRequest): IAuth = BasicAuth(request);
}