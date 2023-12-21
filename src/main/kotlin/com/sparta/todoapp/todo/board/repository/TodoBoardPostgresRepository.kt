package com.sparta.todoapp.todo.board.repository

import com.sparta.todoapp.todo.repository.TodoBoardEntityRepository


class TodoBoardPostgresRepository(
    val todoBoardEntityRepository: TodoBoardEntityRepository
): ITodoBoardRepository {
}