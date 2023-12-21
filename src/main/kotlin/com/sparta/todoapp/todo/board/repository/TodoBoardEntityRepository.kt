package com.sparta.todoapp.todo.repository

import com.sparta.todoapp.todo.entity.TodoBoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoBoardEntityRepository: JpaRepository<TodoBoardEntity, Long> {
}