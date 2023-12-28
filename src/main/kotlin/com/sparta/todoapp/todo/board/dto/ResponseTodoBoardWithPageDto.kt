package com.sparta.todoapp.todo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoBoardWithPageDto (
    @field:JsonProperty("totalPage") val page: Int,
    @field:JsonProperty("boardList") val boardList: List<ResponseTodoBoardDto>
)