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

	/**
	 * TODO:
	 *  1. BoardList를 가지고 온다. (Repository에서 FindAll을 걸었을 때, 숫자가 적을 경우
	 */
//	fun getTodoBoardList(page: Int) =
//		todoBoardRepository.getBoardListByPage(page, 10)
//			.let{}
}
