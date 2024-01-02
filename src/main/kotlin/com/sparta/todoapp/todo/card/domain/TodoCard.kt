package com.sparta.todoapp.todo.card.domain

import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity

data class TodoCard(
	var boardId: Long,
	var title: String,
	var description: String,
	var writer: String,
) {
	companion object {
		fun from(dto: RequestTodoCardDto) = TodoCard(
			boardId = dto.boardId!!,
			description = dto.description!!,
			writer = dto.writer!!,
			title = dto.title!!
		)
	}

	fun toEntity() = TodoCardEntity(
		boardId = boardId,
		title = title,
		todoCardDetailEntity = TodoCardDetailEntity(writer = writer, description = description)
	)

	fun toDetailEntity() = TodoCardDetailEntity(writer = writer, description = description)
}