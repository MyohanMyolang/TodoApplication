package com.sparta.todoapp.auth.service

import com.sparta.todoapp.auth.dto.SignDto

interface AuthService {
    fun signUp(signDto: SignDto): Boolean
    fun signIn(signDto: SignDto): String
}