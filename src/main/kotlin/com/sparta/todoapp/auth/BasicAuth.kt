package com.sparta.todoapp.auth

import jakarta.servlet.http.HttpServletRequest
import java.lang.reflect.Member

class BasicAuth (
    private val request: HttpServletRequest
): IAuth{
    override fun authTest(){
    }

    fun getCurrentMemberKey(): String? {
        return request.getHeader("Authorization");
    }

    fun getCurMember(): Member{
        return 1 as Member
    }
}