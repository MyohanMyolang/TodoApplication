package com.sparta.todoapp.domain.todo.board.service

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardReq
import com.sparta.todoapp.domain.todo.board.entity.TodoBoardEntity
import com.sparta.todoapp.domain.todo.board.repository.ITodoBoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TodoBoardService(
	private val todoBoardRepository: ITodoBoardRepository
) {
	@Transactional
	fun addTodoBoard(dto: TodoBoardReq, currentMember: MemberEntity) =
		TodoBoardEntity.of(dto, currentMember)
			.let { todoBoardRepository.saveTodoBoard(it).toResponse() }

	fun getTodoBoardList(page: Int) =
		todoBoardRepository.getTodoBoardList(page, 10).map{ it.toResponse()}

	fun getTodoBoard(id: Long) =
		todoBoardRepository.getTodoBaord(id).toResponse()
}
