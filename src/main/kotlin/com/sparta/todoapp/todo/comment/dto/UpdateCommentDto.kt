package com.sparta.todoapp.todo.comment.dto

import jakarta.validation.constraints.NotNull

data class UpdateCommentDto(
	@field:NotNull(message = "id는 필수로 입력되어야 합니다.")
	val id: Long? = null,

	@field:NotNull(message = "userName은 필수로 입력되어야 합니다.")
	val userName: String? = null,

	@field:NotNull(message = "userPassword는 필수로 입력되어야 합니다.")
	val userPassword: String? = null,

	@field:NotNull(message = "description은 필수로 입력되어야 합니다.")
	val description: String? = null
)