package com.sparta.todoapp.todo.exception

import com.sparta.todoapp.system.error.ErrorCode
import com.sparta.todoapp.system.error.ErrorObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.sparta.todoapp.todo"])
class TodoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun dtoValidateError(error: MethodArgumentNotValidException): ResponseEntity<ErrorObject> {
        val errorMap = mutableMapOf<String, String>();

        error.bindingResult.fieldErrors.forEach {
            errorMap[it.field] = it.defaultMessage ?: "정의되지 않은 에러";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorObject(ErrorCode.Validation.id, ErrorCode.Validation.message, errorMap));
    }

    @ExceptionHandler(NotFoundTargetException::class)
    fun notFoundTargetError(error: NotFoundTargetException): ResponseEntity<ErrorObject> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorObject(ErrorCode.NotFoundTarget.id, ErrorCode.NotFoundTarget.message, error.message));
    }
}