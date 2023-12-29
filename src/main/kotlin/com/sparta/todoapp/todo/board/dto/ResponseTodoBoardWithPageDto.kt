package com.sparta.todoapp.todo.board.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoBoardWithPageDto(
	@field:JsonProperty("totalPage") val page: Int,
	@field:JsonProperty("boardList") val boardList: List<ResponseTodoBoardDto>
)