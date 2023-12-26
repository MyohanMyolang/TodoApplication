package com.sparta.todoapp.system.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String) {
    NotFoundTarget(HttpStatus.NOT_FOUND, "대상을 찾지 못하였습니다."),
    Validation(HttpStatus.BAD_REQUEST, "Validation을 통과하지 못했습니다."),
    Unauthorized(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

}