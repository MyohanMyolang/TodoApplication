package com.sparta.todoapp.infra.security

import com.sparta.todoapp.common.member.entity.MemberEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
	val member: MemberEntity,
	val authorities: Collection<GrantedAuthority>
) {
	constructor(member: MemberEntity, roles: Set<String>) : this(
		member,
		roles.map { SimpleGrantedAuthority("ROLE_$it") }
	)
}
