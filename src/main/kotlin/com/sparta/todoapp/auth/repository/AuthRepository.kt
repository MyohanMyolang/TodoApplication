package com.sparta.todoapp.auth.repository

import com.sparta.todoapp.common.member.domain.Member
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.MemberEntityRepository

class AuthRepository(private val memberEntityRepository: MemberEntityRepository) : IAuthRepository {
	override fun signUp(memberDomain: Member) = memberEntityRepository.save(memberDomain.toEntity())
	override fun findByMemberId(memberId: String): MemberEntity? = memberEntityRepository.findByMemberId(memberId)
}
