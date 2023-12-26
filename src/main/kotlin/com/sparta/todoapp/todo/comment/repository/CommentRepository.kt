package com.sparta.todoapp.todo.comment.repository

import com.sparta.todoapp.todo.comment.domain.Comment
import com.sparta.todoapp.todo.comment.entity.CommentEntity
import org.springframework.data.repository.findByIdOrNull

class CommentRepository(
    private val commentEntityRepository: CommentEntityRepository
) : ICommentRepository {
    override fun addComment(domain: Comment): CommentEntity = commentEntityRepository.save(domain.toEntity())
    override fun findCommentById(id: Long?): CommentEntity? = commentEntityRepository.findByIdOrNull(id)
    override fun updateComment(domain: Comment, entity: CommentEntity): CommentEntity {
        entity.description = domain.description
        return entity;
    }

    override fun deleteComment(entity: CommentEntity): CommentEntity {
        commentEntityRepository.delete(entity)
        return entity
    }
}