package com.sparta.todoapp.todo.repository

import com.sparta.todoapp.todo.entity.TodoCard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository: JpaRepository<TodoCard, Long> {
}