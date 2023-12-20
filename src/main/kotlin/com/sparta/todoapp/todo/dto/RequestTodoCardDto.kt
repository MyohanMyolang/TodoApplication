package com.sparta.todoapp.todo.dto

import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.entity.TodoCardDetail
import jakarta.validation.constraints.NotNull

class RequestTodoCardDto {
    @NotNull(message = "targetBoardId는 필수 입니다.")
    val targetBoardId: Long? = null;

    @NotNull(message = "작성자는 필수 입니다.")
    val writer: String? = null;

    @NotNull(message = "내용은 필수 입니다.")
    val descript: String? = null;

    @NotNull(message = "제목은 필수 입니다.")
    val title: String? = null;

    fun convertToEntity(): TodoCard {
        val todoCardDetail = TodoCardDetail(writer = writer!!, descript = descript!!)
        return TodoCard(ownerId = targetBoardId!!, title = title!!, todoCardDetail = todoCardDetail)
    }

}