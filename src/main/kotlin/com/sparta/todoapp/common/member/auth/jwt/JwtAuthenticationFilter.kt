package com.sparta.todoapp.common.member.auth.jwt


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
	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val jwt: String? = request.getHeader(HttpHeaders.AUTHORIZATION)?.let {
			if (it.startsWith("Bearer ")) {
				val token = it.split(" ")[1]
				request.setAttribute(ATTRIBUTE_JWT_TOKEN, token)
				token
			} else null
		}

		if (jwt != null) {
			jwtPlugin.validateToken(jwt)
				.onSuccess {
					val userId: String = it.payload.subject
					val role = it.payload.get("role", String::class.java)

					SecurityContextHolder.getContext().authentication = jwtAuthenticationProvider.getAuthentication(userId, role, request)
				}
		}
		filterChain.doFilter(request, response)
	}
}