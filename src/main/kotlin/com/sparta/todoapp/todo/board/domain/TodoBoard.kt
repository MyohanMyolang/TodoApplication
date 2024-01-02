package com.sparta.todoapp.todo.board.domain

import com.sparta.todoapp.todo.board.dto.RequestTodoBoardDto

data class TodoBoard(
	var boardName: String,

) {
	companion object {
		fun from(dto: RequestTodoBoardDto) = TodoBoard(
			boardName = dto.boardName!!
		)
	}

}