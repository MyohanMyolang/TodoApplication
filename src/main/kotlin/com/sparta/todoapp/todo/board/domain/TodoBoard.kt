package com.sparta.todoapp.todo.board.domain

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.board.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.board.entity.TodoBoardEntity

data class TodoBoard(
	var boardName: String
) {
	companion object {
		fun from(dto: RequestTodoBoardDto) = TodoBoard(
			boardName = dto.boardName!!
		)
	}

	fun toEntityFrom(owner: MemberEntity): TodoBoardEntity = TodoBoardEntity(boardName = boardName, owner = owner)
}