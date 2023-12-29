package com.sparta.todoapp.todo.card.dto

import jakarta.validation.constraints.Size

data class UpdateTodoCardDto(
	val boardId: Long?,

	val writer: String?,

	@field:Size(min = 1, max = 1000, message = "내용은 1자 이상 1000자 이하여야 합니다.")
	val description: String?,

	@field:Size(min = 1, max = 200, message = "제목은 1자 이상 200자 이하여야 합니다.")
	val title: String?,
)