package com.sparta.todoapp.api.todo.service

import com.sparta.todoapp.domain.todo.board.service.TodoBoardService
import org.springframework.stereotype.Service

@Service
class TodoBoardApiService(
	private val todoBoardService: TodoBoardService
) {

}
