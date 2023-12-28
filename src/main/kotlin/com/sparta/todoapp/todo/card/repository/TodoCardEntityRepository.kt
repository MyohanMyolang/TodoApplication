package com.sparta.todoapp.todo.card.repository

import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardEntityRepository: JpaRepository<TodoCardEntity, Long> {
    fun findAllByBoardIdOrderByDateDesc(pageable: PageRequest, ownerId: Long): List<TodoCardEntity>;
    fun findAllByBoardIdOrderByDateAsc(pageable: PageRequest, ownerId: Long): List<TodoCardEntity>;
}