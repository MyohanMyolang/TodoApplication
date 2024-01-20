package com.sparta.todoapp.common.member.auth.jwt


import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.IncorrectClaimException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.MissingClaimException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

const val ATTRIBUTE_JWT_TOKEN = "JWT_TOKEN_ATTRIBUTE"


@Component
class JwtAuthenticationFilter(
	private val jwtPlugin: JwtPlugin,
	private val jwtAuthenticationProvider: JwtAuthenticationProvider
) : OncePerRequestFilter() {


	companion object {
		private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
	}

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val jwt = request.getBearerToken()

		if (jwt != null) {
			jwtPlugin.validateToken(jwt)
				.onSuccess {
					val userId: String = it.payload.subject
					val role = it.payload.get("role", String::class.java)

					SecurityContextHolder.getContext().authentication =
						jwtAuthenticationProvider.getAuthentication(userId, role, request)
				}
				.onFailure {
					when (it) {
						is SignatureException -> TODO("서명이 일치하지 않음")
						is MalformedJwtException -> TODO("제대로 된 JWT Token 아님")
						is IncorrectClaimException -> TODO("Claim 이 예상하던 값과 다름")
						is MissingClaimException -> TODO("Claim Token 누락")
						is ExpiredJwtException -> TODO("JWT Token 만료")
						else -> throw TODO("뭔가 에러남 일단")
					}
				}
		}
		filterChain.doFilter(request, response)
	}

	private fun HttpServletRequest.getBearerToken(): String? {
		val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
		return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
	}
}