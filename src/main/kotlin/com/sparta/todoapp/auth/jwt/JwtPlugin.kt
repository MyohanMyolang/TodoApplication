package com.sparta.todoapp.auth.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*


@Component
class JwtPlugin() {

	companion object {
		const val ISSUER = "team.sparta.com"
		const val SECRET = "PO4c8z41Hia5gJG3oeuFJMRYBB4Ws4aZ"
		const val ACCESS_TOKEN_EXPIRATION_HOUR: Long = 168
	}

	fun validateToken(jwt: String): Result<Jws<Claims>> {
		return kotlin.runCatching {
			val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
			Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
		}
	}

	fun generateAccessToken(subject: String, role: String): String {
		return generateToken(subject, role, Duration.ofHours(ACCESS_TOKEN_EXPIRATION_HOUR))
	}


	private fun generateToken(subject: String, role: String, expirationPeriod: Duration): String {
		val claims: Claims = Jwts.claims()
			.add(mapOf("role" to role))
			.build()

		val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
		val now = Instant.now()

		return Jwts.builder()
			.subject(subject)
			.issuer(ISSUER)
			.issuedAt(Date.from(now))
			.expiration(Date.from(now.plus(expirationPeriod)))
			.claims(claims)
			.signWith(key)
			.compact()
	}

}
