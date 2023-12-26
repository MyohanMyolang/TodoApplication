package com.sparta.todoapp.todo.comment.service

import com.sparta.todoapp.todo.comment.domain.Comment
import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto
import com.sparta.todoapp.todo.comment.entity.CommentEntity
import com.sparta.todoapp.todo.comment.repository.ICommentRepository
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.exception.UnauthorizedException
import com.sparta.todoapp.todo.facade.ITodoRepository
import jakarta.transaction.Transactional
import org.hibernate.annotations.NotFound
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: ICommentRepository,
    private val todoRepository: ITodoRepository
) : CommentService {

    private fun findCommentById(id: Long): CommentEntity =
        commentRepository.findCommentById(id) ?: throw NotFoundTargetException("Comment가 존재하지 않습니다.")

    @Transactional
    override fun addComment(requestDto: RequestCommentDto): ResponseCommentDto {
        todoRepository.findCardById(requestDto.cardId!!) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

        return commentRepository.addComment(Comment.from(requestDto)).toResponseDto()
    }

    @Transactional
    override fun updateComment(updateDto: UpdateCommentDto): ResponseCommentDto {
        val findComment = findCommentById(updateDto.id!!)

        val comment = Comment.of(updateDto, findComment.detailCardId)
        if (!comment.checkAuth(findComment)) throw UnauthorizedException("권한이 없습니다.")

        return commentRepository.updateComment(comment, findComment).toResponseDto()
    }

    @Transactional
    override fun deleteComment(deleteDto: DeleteCommentDto): ResponseCommentDto {
        val findComment = findCommentById(deleteDto.id!!)

        val comment = Comment.from(deleteDto)
        if (!comment.checkAuth(findComment)) throw UnauthorizedException("권한이 없습니다.")

        return commentRepository.deleteComment(findComment).toResponseDto();
    }
}