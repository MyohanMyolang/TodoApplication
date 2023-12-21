package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCard

interface CardService {

    fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto
    fun updateTodoCardById(id: Long, updateData: Map<String, Any>): ResponseTodoCardDetailDto
    fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto
}