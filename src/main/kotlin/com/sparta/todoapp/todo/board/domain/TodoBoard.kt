package com.sparta.todoapp.todo.board.domain

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity

data class TodoBoard(
    var boardName: String
) {
    fun from(dto: RequestTodoBoardDto) = TodoBoardEntity(
        boardName = boardName)
}