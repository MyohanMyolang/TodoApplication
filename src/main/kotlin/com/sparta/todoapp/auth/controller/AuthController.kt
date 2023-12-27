package com.sparta.todoapp.auth.controller

import com.sparta.todoapp.auth.dto.SignDto
import com.sparta.todoapp.auth.service.AuthService
import com.sparta.todoapp.global.util.responseEntity
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signDto: SignDto) = responseEntity(HttpStatus.CREATED){
        authService.signUp(signDto)
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid signDto: SignDto) = responseEntity(HttpStatus.OK){
        authService.signIn(signDto)
    }
}