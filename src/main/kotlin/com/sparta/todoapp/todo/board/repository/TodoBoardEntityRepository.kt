package com.sparta.todoapp.todo.board.repository

import com.sparta.todoapp.todo.board.entity.TodoBoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoBoardEntityRepository : JpaRepository<TodoBoardEntity, Long> {
	fun findByBoardName(name: String): TodoBoardEntity?
	fun findAllByBoardName(name: String): List<TodoBoardEntity>
}