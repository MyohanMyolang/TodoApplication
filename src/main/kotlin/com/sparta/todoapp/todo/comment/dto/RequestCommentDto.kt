package com.sparta.todoapp.todo.comment.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RequestCommentDto(
    @field:NotNull(message = "cardId는 필수로 입력되어야 합니다.")
    val cardId: Long? = null,

    @field:NotNull(message = "userName은 필수로 입력되어야 합니다.")
    val userName: String? = null,

    @field:NotNull(message = "userPassword는 필수로 입력되어야 합니다.")
    val userPassword: String? = null,

    @field:NotBlank(message = "description은 필수로 입력되어야 합니다.")
    val description: String? = null
)