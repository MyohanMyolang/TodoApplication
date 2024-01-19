package com.sparta.todoapp.auth

import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity

interface IAuth {
	fun generateKey(signDto: SignDto): String
	fun getCurrentMemberEntity(): MemberEntity
	fun getType(): String
	fun <T> checkPermission(owner: MemberEntity, func: () -> T): T
}