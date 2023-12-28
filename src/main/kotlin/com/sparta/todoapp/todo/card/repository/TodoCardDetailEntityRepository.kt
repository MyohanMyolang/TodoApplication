package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardDetailEntityRepository : JpaRepository<TodoCardDetailEntity, Long> {
}