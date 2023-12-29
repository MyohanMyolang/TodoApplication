package com.sparta.todoapp.common.member.domain

import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity

data class Member(
	var memberId: String,
	var password: String,
	var key: String
) {
	companion object {
		fun of(dto: SignDto, key: String) = Member(
			memberId = dto.id!!,
			password = dto.password!!,
			key = key
		)
	}

	fun isSameIdAndPw(entity: MemberEntity): Boolean = entity.memberId == memberId && entity.password == password

	fun toEntity() = MemberEntity(
		memberId = memberId,
		password = password,
		key = key
	)
}