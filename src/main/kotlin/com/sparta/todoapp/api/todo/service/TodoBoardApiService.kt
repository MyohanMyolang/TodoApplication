package com.sparta.todoapp.api.todo.service

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardReq
import com.sparta.todoapp.domain.todo.board.service.TodoBoardService
import org.springframework.stereotype.Service

@Service
class TodoBoardApiService(
	private val todoBoardService: TodoBoardService
) {
	fun addTodoBoard(dto: TodoBoardReq, currentMember: MemberEntity) =
		todoBoardService.addTodoBoard(dto, currentMember)


}
