package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.TodoCardDto
import com.sparta.todoapp.todo.entity.TodoList
import org.springframework.stereotype.Service

@Service
interface TodoService {
    fun addTodoCard(todoList: TodoList): TodoCardDto
}