package com.sparta.todoapp.todo.card.domain

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.dto.RequestTodoBoardDto

data class TodoCard(
    var boardId: Long,
    var title: String,
    var description: String,
    var writer: String,
) {
    companion object {
        fun from(dto: RequestTodoCardDto) = TodoCard(
            boardId = dto.boardId!!,
            description = dto.description!!,
            writer = dto.writer!!,
            title = dto.title!!
        )
    }

    fun toEntityFrom(owner: MemberEntity) = TodoCardEntity(
        boardId = boardId,
        title = title,
        owner = owner,
        todoCardDetailEntity = TodoCardDetailEntity(writer = writer, description = description)
    )

    fun toDetailEntity() = TodoCardDetailEntity(writer = writer, description = description)
}