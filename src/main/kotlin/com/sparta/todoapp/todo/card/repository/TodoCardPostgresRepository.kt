package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

class TodoCardPostgresRepository(
	private val todoCardEntityRepository: TodoCardEntityRepository,
	private val todoCardDetailEntityRepository: TodoCardDetailEntityRepository
) : ITodoCardRepository {

	private fun getPageRequest(page: Int, size: Int) = PageRequest.of(page, size)

	override fun addCard(todoCard: TodoCard): TodoCardEntity =
		todoCardEntityRepository.save(todoCard.toEntity())

	override fun findCardById(id: Long): TodoCardEntity? = todoCardEntityRepository.findByIdOrNull(id)
	override fun updateDataByDto(findCard: TodoCardEntity, updateData: UpdateTodoCardDto): TodoCardEntity {
		updateData.title?.let { findCard.title = it }
		updateData.writer?.let { findCard.todoCardDetailEntity.writer = it }
		updateData.description?.let { findCard.todoCardDetailEntity.description = it }
		return findCard
	}

	override fun deleteCard(findCard: TodoCardEntity): TodoCardEntity {
		todoCardEntityRepository.delete(findCard)
		return findCard
	}

	override fun getCardListDescByBoardId(id: Long, page: Int, size: Int) =
		todoCardEntityRepository.findAllByBoardIdOrderByDateDesc(getPageRequest(page - 1, size), id)

	override fun getCardListAscByBoardId(id: Long, page: Int, size: Int) =
		todoCardEntityRepository.findAllByBoardIdOrderByDateAsc(getPageRequest(page - 1, size), id)

	override fun completedChange(entity: TodoCardEntity): Boolean {
		entity.isCompleted = !entity.isCompleted
		return entity.isCompleted
	}

	override fun findCardDetailById(id: Long): TodoCardDetailEntity? = todoCardDetailEntityRepository.findByIdOrNull(id)
}