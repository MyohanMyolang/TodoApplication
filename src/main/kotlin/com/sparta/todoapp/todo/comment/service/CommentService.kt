package com.sparta.todoapp.todo.comment.service

import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto

interface CommentService {
    fun addComment(requestDto: RequestCommentDto): ResponseCommentDto
    fun updateComment(updateDto: UpdateCommentDto): ResponseCommentDto
    fun deleteComment(deleteDto: DeleteCommentDto): ResponseCommentDto
}