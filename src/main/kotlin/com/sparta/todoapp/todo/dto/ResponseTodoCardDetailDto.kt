package com.sparta.todoapp.todo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseTodoCardDetailDto(
    @field:JsonProperty private val id:Long,
    @field:JsonProperty private val title: String,
    @field:JsonProperty private val isCompleted: Boolean,
    @field:JsonProperty private val writer: String,
    @field:JsonProperty private val descript: String
)