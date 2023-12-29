package com.sparta.todoapp.auth.Config

import com.sparta.todoapp.auth.BasicAuth
import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.auth.repository.AuthRepository
import com.sparta.todoapp.auth.repository.IAuthRepository
import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {
	@Bean
	fun authConfig1(request: HttpServletRequest, memberEntityRepository: MemberEntityRepository): IAuth =
		BasicAuth(request, memberEntityRepository)

	@Bean
	fun authRepositoryConfig(memberEntityRepository: MemberEntityRepository): IAuthRepository =
		AuthRepository(memberEntityRepository)
}