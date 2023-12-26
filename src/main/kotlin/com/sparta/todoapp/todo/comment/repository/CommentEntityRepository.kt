package com.sparta.todoapp.todo.comment.repository

import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.comment.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentEntityRepository : JpaRepository<CommentEntity, Long> {
    fun findAllByTodoCardDetail(todoCardDetail: TodoCardDetailEntity): List<CommentEntity>
}