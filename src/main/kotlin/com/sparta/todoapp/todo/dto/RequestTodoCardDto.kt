package com.sparta.todoapp.todo.dto

import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.entity.TodoCardDetail
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class RequestTodoCardDto {
    @NotNull(message = "targetBoardId는 필수 입니다.")
    val targetBoardId: Long? = null;

    @NotNull(message = "작성자는 필수 입니다.")
    val writer: String? = null;

    @NotNull(message = "내용은 필수 입니다.")
    @Size(min = 1, max = 1000, message = "내용은 1자 이상 1000자 이하여야 합니다.")
    val descript: String? = null;

    @NotNull(message = "제목은 필수 입니다.")
    @Size(min = 1, max = 200, message = "제목은 1자 이상 200자 이하여야 합니다.")
    val title: String? = null;

}