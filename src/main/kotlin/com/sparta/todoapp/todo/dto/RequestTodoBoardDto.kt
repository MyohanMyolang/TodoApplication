package com.sparta.todoapp.todo.dto

import com.sparta.todoapp.todo.entity.TodoBoard
import jakarta.validation.constraints.NotBlank

class RequestTodoBoardDto {

    @NotBlank(message = "ownerName은 무조건 입력 되어야 합니다.")
    val ownerName: String? = null;


}