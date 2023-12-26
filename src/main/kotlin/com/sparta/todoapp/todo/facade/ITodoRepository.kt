package com.sparta.todoapp.todo.facade

import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.comment.entity.CommentEntity
import com.sparta.todoapp.todo.entity.TodoBoardEntity

interface ITodoRepository {
    fun findBoardById(id: Long): TodoBoardEntity?
    fun findCardById(id: Long): TodoCardEntity?
    fun findCommentListByTodoCardDetailEntity(todoCardDetailEntity: TodoCardDetailEntity): List<CommentEntity>
}