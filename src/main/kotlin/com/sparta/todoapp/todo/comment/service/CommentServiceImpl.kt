package com.sparta.todoapp.todo.comment.service

import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import com.sparta.todoapp.system.error.exception.UnauthorizedException
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.comment.domain.Comment
import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto
import com.sparta.todoapp.todo.comment.entity.CommentEntity
import com.sparta.todoapp.todo.comment.repository.ICommentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: ICommentRepository,
    private val todoCardRepository: ITodoCardRepository
) : CommentService {

    override fun getCommentById(id: Long): CommentEntity =
        commentRepository.findCommentById(id) ?: throw NotFoundTargetException("Comment가 존재하지 않습니다.")

    @Transactional
    override fun addComment(requestDto: RequestCommentDto): ResponseCommentDto {
        todoCardRepository.findCardById(requestDto.cardId!!) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

        return commentRepository.addComment(Comment.from(requestDto)).toResponseDto()
    }

    @Transactional
    override fun updateComment(updateDto: UpdateCommentDto): ResponseCommentDto {
        val findComment = getCommentById(updateDto.id!!)

        val comment = Comment.of(updateDto, findComment.cardDetailId)
        if (!comment.checkAuth(findComment)) throw UnauthorizedException("권한이 없습니다.")

        return commentRepository.updateComment(comment, findComment).toResponseDto()
    }

    @Transactional
    override fun deleteComment(deleteDto: DeleteCommentDto): ResponseCommentDto {
        val findComment = getCommentById(deleteDto.id!!)

        val comment = Comment.from(deleteDto)
        if (!comment.checkAuth(findComment)) throw UnauthorizedException("권한이 없습니다.")

        return commentRepository.deleteComment(findComment).toResponseDto()
    }

    override fun getCommentListByCardId(cardId: Long): List<ResponseCommentDto> =
        commentRepository.findAllByTodoCardDetailId(cardId).run {
            val result = mutableListOf<ResponseCommentDto>()
            this.forEach { result.add(it.toResponseDto()) }
            result
        }

}