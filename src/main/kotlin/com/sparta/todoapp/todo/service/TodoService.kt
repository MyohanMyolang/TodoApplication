package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.entity.TodoList
import org.springframework.stereotype.Service

@Service
interface TodoService {
    fun addTodoCard(todoList: TodoList): RequestTodoCardDto
    fun addTodoBoard(todoBoard: TodoBoard): TodoBoard;
}