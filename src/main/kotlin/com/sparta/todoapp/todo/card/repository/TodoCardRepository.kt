package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.todo.card.entity.TodoCard
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository: JpaRepository<TodoCard, Long> {
    fun findAllByBoardIdOrderByDateDesc(pageable: PageRequest, ownerId: Long): List<TodoCard>;
    fun findAllByBoardIdOrderByDateAsc(pageable: PageRequest, ownerId: Long): List<TodoCard>;
}