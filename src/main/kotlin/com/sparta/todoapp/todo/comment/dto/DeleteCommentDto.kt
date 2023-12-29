package com.sparta.todoapp.todo.comment.dto

import jakarta.validation.constraints.NotNull

data class DeleteCommentDto(
	@field:NotNull(message = "id는 필수 입니다.")
	val id: Long? = null,

	@field:NotNull(message = "userName은 필수 입니다.")
	val userName: String? = null,

	@field:NotNull(message = "userPassword는 필수 입니다.")
	val userPassword: String? = null

)
