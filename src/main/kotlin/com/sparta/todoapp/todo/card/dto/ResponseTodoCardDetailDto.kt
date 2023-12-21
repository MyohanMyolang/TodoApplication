package com.sparta.todoapp.todo.card.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoCardDetailDto(
    @field:JsonProperty("id") private val id: Long,
    @field:JsonProperty("title") private val title: String,
    @field:JsonProperty("isCompleted") private val isCompleted: Boolean,
    @field:JsonProperty("writer") private val writer: String,
    @field:JsonProperty("description") private val description: String
)