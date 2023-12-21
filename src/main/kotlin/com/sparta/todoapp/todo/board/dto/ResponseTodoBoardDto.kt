package com.sparta.todoapp.todo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto

data class ResponseTodoBoardDto(
    @field:JsonProperty("id") private val id: Long,
    @field:JsonProperty("ownerName") private val ownerName: String,
    @field:JsonProperty("todoCardList") private val todoCardList: List<ResponseTodoCardDto>? = null
)