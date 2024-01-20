package com.sparta.todoapp.domain.todo.board.repository

import com.sparta.todoapp.domain.todo.board.entity.TodoBoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoBoardEntityRepository: JpaRepository<TodoBoardEntity, Long> {
}