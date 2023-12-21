package com.sparta.todoapp.todo.repository

import com.sparta.todoapp.todo.entity.TodoBoard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoBoardRepository: JpaRepository<TodoBoard, Long> {
}