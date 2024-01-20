package com.sparta.todoapp.common.member.auth.dto

import jakarta.validation.constraints.NotBlank

data class SignDto(
	@field:NotBlank(message = "id는 비어있으면 안됩니다.")
	val id: String? = null,

	@field:NotBlank(message = "password는 비어있으면 안됩니다.")
	val password: String? = null
)