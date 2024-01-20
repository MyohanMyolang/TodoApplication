package com.sparta.todoapp.common.member.auth.jwt

import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import com.sparta.todoapp.infra.security.UserPrincipal
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
	private val memberEntityRepository: MemberEntityRepository
) {

	fun generateAuthenticationToken(principal: UserPrincipal, request: HttpServletRequest) =
		JwtAuthenticationToken(
			principal = principal,
			details = WebAuthenticationDetailsSource().buildDetails(request)
		)

	fun loadUser(userId: String, role: String) =
		memberEntityRepository.findByIdOrNull(userId)
			?.let { UserPrincipal(it, setOf(role)) }
			?: throw NotFoundTargetException("존재하지 않는 유저입니다.")

	fun getAuthentication(userId: String, role: String, request: HttpServletRequest) =
		loadUser(userId, role)
			.let { generateAuthenticationToken(it, request) }
}