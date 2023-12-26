package com.sparta.todoapp.todo.board.domain

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity

data class TodoBoard(
    var boardName: String
) {
    companion object {
        fun from(dto: RequestTodoBoardDto) = TodoBoard(
            boardName = dto.boardName!!
        )
    }

    fun toEntity(): TodoBoardEntity = TodoBoardEntity(boardName = boardName)
}