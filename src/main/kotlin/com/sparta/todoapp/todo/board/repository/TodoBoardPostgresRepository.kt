package com.sparta.todoapp.todo.board.repository

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.board.entity.TodoBoardEntity
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull


class TodoBoardPostgresRepository(
	private val todoBoardEntityRepository: TodoBoardEntityRepository
) : ITodoBoardRepository {
	override fun findAll(): MutableList<TodoBoardEntity> = todoBoardEntityRepository.findAll()
	override fun paginationFindAll(page: Int, size: Int) =
		todoBoardEntityRepository.findAll(PageRequest.of(page - 1, size))

	override fun addBoard(todoBoard: TodoBoard, owner: MemberEntity): TodoBoardEntity {
		val entity = TodoBoardEntity.from(todoBoard, owner)
		return todoBoardEntityRepository.save(entity)
	}

	override fun findBoardListByName(name: String): List<TodoBoardEntity> {
		return todoBoardEntityRepository.findAllByBoardName(name)
	}


	override fun findBoardById(id: Long) = todoBoardEntityRepository.findByIdOrNull(id)

}