package com.sparta.todoapp.common.member.exception

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberExceptionAdvice {

	@ExceptionHandler
	fun passwordNotEqualError(error: PasswordNotEqualException) = responseEntity(HttpStatus.NOT_FOUND){
		ErrorObject(message = "계정정보가 맞지 않습니다.", payload = error.message)
	}
}