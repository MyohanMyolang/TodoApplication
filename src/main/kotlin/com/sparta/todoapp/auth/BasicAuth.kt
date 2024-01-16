package com.sparta.todoapp.auth

import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import com.sparta.todoapp.system.error.exception.AccessAuthException
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import com.sparta.todoapp.system.error.exception.UnauthorizedException
import jakarta.servlet.http.HttpServletRequest
import org.apache.logging.log4j.util.Base64Util


/**
 * NOTE:
 *  1. 로그인 진행 후 토큰가져온다.
 *  2. 프론트 엔드 -> 토큰을 HttpHeader Authorization에 담는다.
 *  3. 인증작업을 거칠 때 Authorization을 확인한다.
 *  4. 확인 작업 후 권한이 없다면 Exception을 던진다.
 */

class BasicAuth(
	private val request: HttpServletRequest,
	private val memberEntityRepository: MemberEntityRepository
) : IAuth {

	fun getCurrentMemberKey(): String {
		return request.getHeader("Authorization") ?: throw UnauthorizedException("로그인이 되어있지 않습니다.")
	}

	override fun getCurrentMemberEntity(): MemberEntity {
		val key = getCurrentMemberKey().split(" ")[1]
		return memberEntityRepository.findByKey(key) ?: throw NotFoundTargetException("로그인 상태를 확인하여 주십시오.")
	}

	override fun getType(): String = "basic"

	override fun generateKey(signDto: SignDto): String {
		return Base64Util.encode("${signDto.id}:${signDto.password}")
	}

	override fun <T> checkAuth(owner: MemberEntity, func: () -> T): T {
		val headerAuth = getCurrentMemberKey()
		val splitHeader = headerAuth.split(" ")
		val type = splitHeader[0]
		val key = splitHeader[1]
		if (owner.key != key) throw AccessAuthException("접근 권한이 없습니다.")
		return func()
	}
}