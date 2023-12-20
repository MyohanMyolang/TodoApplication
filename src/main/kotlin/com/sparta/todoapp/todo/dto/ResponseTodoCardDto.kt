package com.sparta.todoapp.todo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseTodoCardDto(
    @field:JsonProperty("id") private val id: Long,
    @field:JsonProperty("title") private val title: String,
    @field:JsonProperty("isCompleted") private val isCompleted: Boolean,
    @field:JsonProperty("date") private val date: LocalDateTime
)