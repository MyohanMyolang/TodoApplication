package com.sparta.todoapp.domain.todo.board.service

import com.sparta.todoapp.domain.todo.board.repository.ITodoBoardRepository
import org.springframework.stereotype.Service

@Service
class TodoBoardService(
	private val todoBoardRepository: ITodoBoardRepository
) {

}
