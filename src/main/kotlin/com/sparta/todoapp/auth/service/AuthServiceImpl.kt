package com.sparta.todoapp.auth.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.auth.repository.IAuthRepository
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val auth: IAuth,
    private val authRepository: IAuthRepository
) : AuthService {

}