package com.sparta.todoapp.todo.exception

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorCode
import com.sparta.todoapp.system.error.ErrorObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.sparta.todoapp.todo"])
class TodoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun dtoValidateError(error: MethodArgumentNotValidException) = responseEntity(ErrorCode.Validation.status) {
        val errorMap = mutableMapOf<String, String>();

        error.bindingResult.fieldErrors.forEach {
            errorMap[it.field] = it.defaultMessage ?: "정의되지 않은 에러";
        }

        ErrorObject(ErrorCode.Validation.message, errorMap)
    }

    @ExceptionHandler(NotFoundTargetException::class)
    fun notFoundTargetError(error: NotFoundTargetException) = responseEntity(ErrorCode.NotFoundTarget.status) {
        ErrorObject(ErrorCode.NotFoundTarget.message, error.message)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedError(error: UnauthorizedException) = responseEntity(ErrorCode.Unauthorized.status) {
        ErrorObject(ErrorCode.Unauthorized.message, error.message)
    }
}