package com.sparta.todoapp.todo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoBoardDto(
    @field:JsonProperty("id") private val id: Long,
    @field:JsonProperty("ownerName") private val ownerName: String
) {
}