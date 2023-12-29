package com.sparta.todoapp.system.error

import io.swagger.v3.oas.annotations.media.Schema

data class ErrorObject(
	val message: String,
	@field:Schema(description = "에러 사항에 대한 상세 정보가 객체 또는 문자열로 들어옵니다.")
	val payload: Any
)