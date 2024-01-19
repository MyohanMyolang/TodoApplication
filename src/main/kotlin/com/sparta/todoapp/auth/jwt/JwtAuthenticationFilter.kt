package com.sparta.todoapp.auth.jwt


import com.sparta.todoapp.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

const val ATTRIBUTE_JWT_TOKEN = "JWT_TOKEN_ATTRIBUTE"


@Component
class JwtAuthenticationFilter(
	private val jwtPlugin: JwtPlugin
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

					val principal = UserPrincipal(
						id = userId,
						roles = setOf(role)
					)

					val authentication = JwtAuthenticationToken(
						principal = principal,
						details = WebAuthenticationDetailsSource().buildDetails(request)
					)
					SecurityContextHolder.getContext().authentication = authentication
				}
		}
		filterChain.doFilter(request, response)
	}
}