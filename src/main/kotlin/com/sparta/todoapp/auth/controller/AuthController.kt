package com.sparta.todoapp.auth.controller

import com.sparta.todoapp.auth.service.AuthService
import com.sparta.todoapp.global.util.responseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping("/signup")
    fun signUp(){

    }

    fun signIn(){

    }
}