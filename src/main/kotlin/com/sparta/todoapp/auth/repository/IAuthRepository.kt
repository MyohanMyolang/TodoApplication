package com.sparta.todoapp.auth.repository

import com.sparta.todoapp.common.member.domain.Member
import com.sparta.todoapp.common.member.entity.MemberEntity

interface IAuthRepository {

	fun signUp(memberDomain: Member): MemberEntity
	fun findByMemberId(memberId: String): MemberEntity?
}
