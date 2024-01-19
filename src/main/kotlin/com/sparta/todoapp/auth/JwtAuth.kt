package com.sparta.todoapp.auth

import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.auth.jwt.ATTRIBUTE_JWT_TOKEN
import com.sparta.todoapp.auth.jwt.JwtPlugin
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import com.sparta.todoapp.common.member.type.UserRole
import com.sparta.todoapp.system.error.exception.AccessAuthException
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import jakarta.servlet.http.HttpServletRequest

class JwtAuth(
	private val jwtPlugin: JwtPlugin,
	private val request: HttpServletRequest,
	private val memberEntityRepository: MemberEntityRepository
) : IAuth {

	private fun getCurrentMemberKey() = request.getAttribute(ATTRIBUTE_JWT_TOKEN) as String

	override fun generateKey(signDto: SignDto): String =
		jwtPlugin.generateAccessToken(signDto.id!!, UserRole.MEMBER.name)


	override fun getCurrentMemberEntity(): MemberEntity =
		getCurrentMemberKey()
			.let {
				memberEntityRepository.findByKey(it) ?: throw NotFoundTargetException("로그인 상태를 확인하여 주십시오.")
			}



	override fun getType(): String = "Bearer"

	override fun <T> checkPermission(owner: MemberEntity, func: () -> T): T {
		val key = getCurrentMemberKey()
		if (owner.key != key) throw AccessAuthException("접근 권한이 없습니다.")
		return func.invoke()
	}
}