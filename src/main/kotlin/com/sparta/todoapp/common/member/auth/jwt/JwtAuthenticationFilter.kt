package com.sparta.todoapp.common.member.auth.jwt


import io.jsonwebtoken.MalformedJwtException
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
						is MalformedJwtException -> TODO("제대로 된 JWT토큰 아님")
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