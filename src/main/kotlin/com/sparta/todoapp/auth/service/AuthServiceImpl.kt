package com.sparta.todoapp.auth.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.auth.repository.IAuthRepository
import com.sparta.todoapp.common.member.domain.Member
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val auth: IAuth,
    private val authRepository: IAuthRepository
) : AuthService {

    fun getMemberByMemberId(memberId: String) =
        authRepository.findByMemberId(memberId) ?: throw TODO("TheresNoMember Exception")

    @Transactional
    override fun signUp(signDto: SignDto): Boolean {
        if(authRepository.findByMemberId(signDto.id!!) != null) throw TODO("이미 존재하는 Id입니다.")
        authRepository.signUp(Member.of(signDto, auth.generateKey(signDto)))
        return true
    }

    override fun signIn(signDto: SignDto): String {
        val memberEntity = getMemberByMemberId(signDto.id!!)
        if (!Member.of(signDto, "").isSameIdAndPw(memberEntity)) throw TODO("로그인 실패")

        return memberEntity.key!!
    }

}