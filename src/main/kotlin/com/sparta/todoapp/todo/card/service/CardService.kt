package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.todo.card.dto.*
import com.sparta.todoapp.todo.card.entity.TodoCardEntity

interface CardService {

    fun addTodoCard(requestTodoCard: RequestTodoCardDto): ResponseTodoCardDetailDto
    fun updateTodoCardById(id: Long, updateData: UpdateTodoCardDto): ResponseTodoCardDetailDto
    fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto
    fun getSortedCardList(id: Long, page: Int, size: Int, sort: String): List<ResponseTodoCardWithCommentListDto>
    fun completedChange(id: Long): Boolean
    fun getTodoCardDetailByIdWithCommentList(id: Long): ResponseTodoCardDetailWithCommentListDto
    fun getTodoCardById(id: Long): TodoCardEntity
}