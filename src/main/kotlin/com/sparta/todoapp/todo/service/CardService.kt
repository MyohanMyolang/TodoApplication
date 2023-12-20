package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.entity.TodoCard

interface CardService {

    fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto
    fun updateTodoCardById(id: Long, updateData: Map<String, Any>): ResponseTodoCardDetailDto
    fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto
}