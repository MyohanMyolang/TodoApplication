package com.sparta.todoapp.todo.repository

import org.springframework.stereotype.Repository

@Repository
interface TodoRepository {
    fun addTodoList()
}