package com.sparta.todoapp.domain.todo.board.dto

import jakarta.validation.constraints.NotBlank


data class TodoBoardReq(
	@field:NotBlank(message = "Board Name은 비어있으면 안됩니다.")
	val boardName: String? = null
)