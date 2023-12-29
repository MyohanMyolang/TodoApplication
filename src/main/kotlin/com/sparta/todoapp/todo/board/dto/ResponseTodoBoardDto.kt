package com.sparta.todoapp.todo.board.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoBoardDto(
	@field:JsonProperty("id") private val id: Long,
	@field:JsonProperty("boardName") private val ownerName: String,
)