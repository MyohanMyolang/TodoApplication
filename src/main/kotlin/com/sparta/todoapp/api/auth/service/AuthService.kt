package com.sparta.todoapp.api.auth.service

import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.common.member.auth.jwt.JwtPlugin
import com.sparta.todoapp.common.member.exception.PasswordNotEqualException
import com.sparta.todoapp.common.member.service.MemberService
import com.sparta.todoapp.common.member.type.UserRole
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
	private val memberService: MemberService,
	private val jwtPlugin: JwtPlugin,
	private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
	fun signUp(signDto: SignDto) =
		signDto.copy(password = bCryptPasswordEncoder.encode(signDto.password))
			.let { memberService.addMember(it, UserRole.MEMBER) }



	fun signIn(signDto: SignDto) =
		memberService.getMember(signDto.id!!)
			.takeIf { bCryptPasswordEncoder.matches(signDto.password, it.password) }
			?.let { jwtPlugin.generateAccessToken(it.memberId, it.role.name) }
			?: throw PasswordNotEqualException("비밀번호가 일치하지 않습니다.")

}
