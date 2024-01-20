package com.sparta.todoapp.api.auth.controller

import com.sparta.todoapp.api.auth.service.AuthService
import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.attribute.UserPrincipal

@RestController
@RequestMapping("/auth")
class AuthController(
	private val authService: AuthService
) {


	@Operation(summary = "회원가입")
	@ApiResponses(
		value = [ApiResponse(responseCode = "201", description = "회원가입 완료"), ApiResponse(
			responseCode = "409",
			description = "이미 존재하는 회원일 경우 발생합니다.",
			content = [(Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class)))]
		), ApiResponse(
			responseCode = "400",
			description = "Validation을 통과하지 못하였을 경우 실패한 부분만 key와 실패 사유가 나오게 됩니다.",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		)]
	)
	@PostMapping("/signup")
	fun signUp(@RequestBody @Valid signDto: SignDto) = responseEntity(HttpStatus.CREATED) {
		authService.signUp(signDto)
	}


	@Operation(summary = "로그인", description = "key를 반환합니다. Header [Authorization]에 등록시켜 주십시오.")
	@ApiResponses(
		value = [ApiResponse(responseCode = "200", description = "로그인 성공"), ApiResponse(
			responseCode = "400",
			description = "Validation을 통과하지 못하였을 경우 실패한 부분만 key와 실패 사유가 나오게 됩니다.",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		), ApiResponse(
			responseCode = "404",
			description = "로그인 실패",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		)]
	)
	@PostMapping("/signin")
	fun signIn(@RequestBody @Valid signDto: SignDto) = responseEntity(HttpStatus.OK) {
		authService.signIn(signDto)
	}
}