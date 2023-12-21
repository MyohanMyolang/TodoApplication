package com.sparta.todoapp.todo.dto

import jakarta.validation.constraints.NotBlank

class RequestTodoBoardDto {

    @NotBlank(message = "boardName은 무조건 입력 되어야 합니다.")
    val boardName: String? = null;

}