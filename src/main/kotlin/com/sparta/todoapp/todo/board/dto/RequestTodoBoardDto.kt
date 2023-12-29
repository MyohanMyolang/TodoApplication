package com.sparta.todoapp.todo.board.dto

import jakarta.validation.constraints.NotBlank

data class RequestTodoBoardDto(
	@field:NotBlank(message = "boardName은 무조건 입력 되어야 합니다.")
	val boardName: String?,
)