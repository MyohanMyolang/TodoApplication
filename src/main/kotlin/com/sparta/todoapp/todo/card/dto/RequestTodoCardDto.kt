package com.sparta.todoapp.todo.card.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestTodoCardDto(
	@field:NotNull(message = "boardId는 필수 입니다.")
	val boardId: Long? = null,

	@field:NotNull(message = "작성자는 필수 입니다.")
	val writer: String? = null,

	@field:NotNull(message = "내용은 필수 입니다.")
	@field:Size(min = 1, max = 1000, message = "내용은 1자 이상 1000자 이하여야 합니다.")
	val description: String? = null,

	@field:NotNull(message = "제목은 필수 입니다.")
	@field:Size(min = 1, max = 200, message = "제목은 1자 이상 200자 이하여야 합니다.")
	val title: String? = null,
)