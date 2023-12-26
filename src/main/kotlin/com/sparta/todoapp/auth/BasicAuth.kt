package com.sparta.todoapp.auth

import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import jakarta.servlet.http.HttpServletRequest
import java.lang.reflect.Member

class BasicAuth (
    private val request: HttpServletRequest,
    private val memberEntityRepository: MemberEntityRepository
): IAuth{

    fun getCurrentMemberKey(): String? {
        return request.getHeader("Authorization");
    }

    fun getCurMember(): Member{
        return 1 as Member
    }
}