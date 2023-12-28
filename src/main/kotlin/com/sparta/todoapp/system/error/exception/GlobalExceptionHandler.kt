package com.sparta.todoapp.system.error.exception

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorCode
import com.sparta.todoapp.system.error.ErrorObject
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

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

    @ExceptionHandler(AlreadyHasMember::class)
    fun alreadyHasMemberError(error: AlreadyHasMember) = responseEntity(ErrorCode.AlreadyHasMember.status) {
        ErrorObject(ErrorCode.AlreadyHasMember.message, error.message)
    }

    @ExceptionHandler(AccessAuthException::class)
    fun accessAuthError(error: AccessAuthException) = responseEntity(ErrorCode.Forbidden.status) {
        ErrorObject(ErrorCode.Forbidden.message, error.message)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationError(error: ConstraintViolationException) = responseEntity(ErrorCode.Validation.status) {
        val errorMap = mutableMapOf<String, String>();
        error.constraintViolations.forEachIndexed { index, it ->
            errorMap["reason_$index"] = it.messageTemplate
        }
        ErrorObject(ErrorCode.Validation.message, errorMap)
    }
}