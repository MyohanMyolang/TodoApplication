package com.sparta.todoapp.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
	val id: String,
	val authorities: Collection<GrantedAuthority>
) {
	constructor(id: String, roles: Set<String>) : this(
		"test31fdsafdsaf2312313",
		roles.map { SimpleGrantedAuthority("ROLE_$it") }
	)
}
