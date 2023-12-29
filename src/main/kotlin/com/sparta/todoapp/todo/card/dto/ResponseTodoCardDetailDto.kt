package com.sparta.todoapp.todo.card.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseTodoCardDetailDto(
	@field:JsonProperty("id") val id: Long,
	@field:JsonProperty("title") val title: String,
	@field:JsonProperty("isCompleted") val isCompleted: Boolean,
	@field:JsonProperty("writer") val writer: String,
	@field:JsonProperty("description") val description: String,
	@field:JsonProperty("date") val date: LocalDateTime
)