package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.TodoCardDto
import com.sparta.todoapp.todo.entity.TodoList
import com.sparta.todoapp.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl @Autowired constructor(
    private val todoRepository: TodoRepository
): TodoService {
    override fun addTodoCard(todoList: TodoList): TodoCardDto {
        TODO("Not yet implemented")
    }
}