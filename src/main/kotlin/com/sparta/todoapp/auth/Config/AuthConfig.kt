package com.sparta.todoapp.auth.Config

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.auth.JwtAuth
import com.sparta.todoapp.auth.jwt.JwtPlugin
import com.sparta.todoapp.auth.repository.AuthRepository
import com.sparta.todoapp.auth.repository.IAuthRepository
import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AuthConfig {
	@Bean
	fun authConfig1(
		jwtPlugin: JwtPlugin,
		request: HttpServletRequest,
		memberEntityRepository: MemberEntityRepository
	): IAuth =
		JwtAuth(jwtPlugin, request, memberEntityRepository)

	@Bean
	fun authRepositoryConfig(memberEntityRepository: MemberEntityRepository): IAuthRepository =
		AuthRepository(memberEntityRepository)

	@Bean
	fun passwordEncoder() = BCryptPasswordEncoder()
}