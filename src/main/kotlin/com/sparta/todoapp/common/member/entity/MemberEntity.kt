package com.sparta.todoapp.common.member.entity

import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.common.member.type.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
	@Id @Column(name = "member_id", unique = true)
	val memberId: String,

	@Column(name = "password", nullable = false)
	var password: String,

	@Enumerated(value = EnumType.STRING)
	var role: UserRole

) {
	companion object {
		fun of(dto: SignDto, role: UserRole) = MemberEntity(
			memberId = dto.id!!,
			password = dto.password!!,
			role = role
		)
	}
}