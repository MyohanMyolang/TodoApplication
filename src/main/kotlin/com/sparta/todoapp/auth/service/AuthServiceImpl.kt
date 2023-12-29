package com.sparta.todoapp.auth.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.auth.repository.IAuthRepository
import com.sparta.todoapp.common.member.domain.Member
import com.sparta.todoapp.system.error.exception.AlreadyHasMember
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
	private val auth: IAuth,
	private val authRepository: IAuthRepository
) : AuthService {

	fun getMemberByMemberId(memberId: String) =
		authRepository.findByMemberId(memberId) ?: throw NotFoundTargetException("해당 Id는 존재하지 않습니다.")

	@Transactional
	override fun signUp(signDto: SignDto): Boolean {
		if (authRepository.findByMemberId(signDto.id!!) != null) throw AlreadyHasMember("id가 중복되었습니다.")
		authRepository.signUp(Member.of(signDto, auth.generateKey(signDto)))
		return true
	}

	override fun signIn(signDto: SignDto): String {
		val memberEntity = getMemberByMemberId(signDto.id!!)
		if (!Member.of(signDto, "").isSameIdAndPw(memberEntity)) throw NotFoundTargetException("비밀번호가 틀립니다.")

		return "basic ${memberEntity.key!!}"
	}

}