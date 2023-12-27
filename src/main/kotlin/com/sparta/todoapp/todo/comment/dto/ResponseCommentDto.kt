package com.sparta.todoapp.todo.comment.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class ResponseCommentDto(
    @field:JsonProperty("id")
    val id: Long,

    @field:JsonProperty("userName")
    val userName: String,

    @field:JsonProperty("description")
    val description: String
)