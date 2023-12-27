package com.sparta.todoapp.todo.comment.repository

import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.comment.domain.Comment
import com.sparta.todoapp.todo.comment.entity.CommentEntity

interface ICommentRepository {
    fun addComment(domain: Comment): CommentEntity
    fun findCommentById(id: Long?): CommentEntity?
    fun updateComment(domain: Comment, entity: CommentEntity): CommentEntity
    fun deleteComment(entity: CommentEntity): CommentEntity
    fun findAllByTodoCardDetail(cardId: Long): List<CommentEntity>
}