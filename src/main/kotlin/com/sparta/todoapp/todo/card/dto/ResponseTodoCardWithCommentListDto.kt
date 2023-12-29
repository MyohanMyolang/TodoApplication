package com.sparta.todoapp.todo.card.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto

data class ResponseTodoCardWithCommentListDto(
	@field:JsonProperty("todoCard")
	val todoCard: ResponseTodoCardDto,

	@field:JsonProperty("commentList")
	val commentList: List<ResponseCommentDto>
)
