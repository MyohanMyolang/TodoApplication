package com.sparta.todoapp.todo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.sparta.todoapp.todo"])
class TodoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun dtoValidateError(error: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errorMap = mutableMapOf<String, String>();

        error.bindingResult.fieldErrors.forEach {
            errorMap[it.field] = it.defaultMessage ?: "정의되지 않은 에러";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
}