package com.sparta.todoapp.common.member.service

import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.IMemberRepository
import com.sparta.todoapp.common.member.type.UserRole
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
	private val memberRepository: IMemberRepository
) {
	@Transactional
	fun addMember(dto: SignDto, role: UserRole): Unit {
		memberRepository.save(MemberEntity.of(dto, role))
	}

	fun getMember(id: String) =
		memberRepository.findById(id)
}