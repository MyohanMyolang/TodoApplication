package com.sparta.todoapp.infra.security.config

import com.sparta.todoapp.auth.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
	private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {

	@Bean
	fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
		return http
			.httpBasic { it.disable() }
			.formLogin { it.disable() }
			.csrf { it.disable() }
			.cors { it.disable() }
			.authorizeHttpRequests {
				it.requestMatchers(
					"/auth/**",
					"/swagger-ui/**",
					"/v3/api-docs/**",
				).permitAll()
					.anyRequest().authenticated()
			}
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
			.build()
	}
}