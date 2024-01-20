package com.sparta.todoapp.domain.todo.board.repository

import com.sparta.todoapp.domain.todo.board.entity.TodoBoardEntity

interface ITodoBoardRepository {
	fun saveTodoBoard(entity: TodoBoardEntity): TodoBoardEntity
}
