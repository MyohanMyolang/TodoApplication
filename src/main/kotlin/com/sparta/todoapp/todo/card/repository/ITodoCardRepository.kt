package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity

interface ITodoCardRepository {
	fun findCardById(id: Long): TodoCardEntity?
	fun updateDataByDto(findCard: TodoCardEntity, updateData: UpdateTodoCardDto): TodoCardEntity
	fun deleteCard(findCard: TodoCardEntity): TodoCardEntity
	fun getCardListDescByBoardId(id: Long, page: Int, size: Int): List<TodoCardEntity>
	fun getCardListAscByBoardId(id: Long, page: Int, size: Int): List<TodoCardEntity>
	fun completedChange(entity: TodoCardEntity): Boolean
	fun findCardDetailById(id: Long): TodoCardDetailEntity?
	fun addCard(todoCard: TodoCard, owner: MemberEntity): TodoCardEntity
}