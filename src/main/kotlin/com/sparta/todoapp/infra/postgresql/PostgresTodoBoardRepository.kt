package com.sparta.todoapp.infra.postgresql

import com.sparta.todoapp.domain.todo.board.entity.TodoBoardEntity
import com.sparta.todoapp.domain.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.domain.todo.board.repository.TodoBoardEntityRepository
import org.springframework.stereotype.Repository

@Repository
class PostgresTodoBoardRepository(
	private val todoBoardEntityRepository: TodoBoardEntityRepository
) : ITodoBoardRepository {

	override fun saveTodoBoard(entity: TodoBoardEntity) =
		todoBoardEntityRepository.save(entity)

	fun getTodoBoardList(page: Int){

	}
}