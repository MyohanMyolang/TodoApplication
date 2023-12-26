package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardEntity

interface ITodoCardRepository {
    fun addCard(todoCard: TodoCard): TodoCardEntity
    fun findCardById(id: Long): TodoCardEntity?
    fun updateDataByDto(findCard: TodoCardEntity, updateData: UpdateTodoCardDto): TodoCardEntity
    fun deleteCard(findCard: TodoCardEntity): TodoCardEntity
    fun getCardListDescByBoardId(id: Long, page: Int, size: Int): List<TodoCardEntity>
    fun getCardListAscByBoardId(id: Long, page: Int, size: Int): List<TodoCardEntity>
    fun completedChange(entity: TodoCardEntity): Boolean
}