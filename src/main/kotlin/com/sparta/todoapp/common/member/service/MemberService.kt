package com.sparta.todoapp.common.member.service

import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.IMemberRepository
import com.sparta.todoapp.common.member.type.UserRole
import org.springframework.stereotype.Service

@Service
class MemberService(
	private val memberRepository: IMemberRepository
) {
	fun addMember(dto: SignDto, role: UserRole) =
		memberRepository.save(MemberEntity.of(dto, role))

	fun getMember(id: String) =
		memberRepository.findById(id)
}