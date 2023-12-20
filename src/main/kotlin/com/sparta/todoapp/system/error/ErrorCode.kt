package com.sparta.todoapp.system.error

enum class ErrorCode(val id: Int, val message: String) {
    NotFoundTarget(400, "대상을 찾지 못하였습니다."),
    Validation(401, "Validation을 통과하지 못했습니다.");


}