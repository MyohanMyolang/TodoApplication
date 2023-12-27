package com.sparta.todoapp.todo.card.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto

class ResponseTodoCardDetailWithCommentListDto(
    @field:JsonProperty("todoCardDetail")
    val todoCardDetail: ResponseTodoCardDetailDto,

    @field:JsonProperty("commentList")
    val commentList: List<ResponseCommentDto>
)