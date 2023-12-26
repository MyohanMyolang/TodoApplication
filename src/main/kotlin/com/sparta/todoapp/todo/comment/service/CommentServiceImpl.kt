package com.sparta.todoapp.todo.comment.service

import com.sparta.todoapp.todo.comment.repository.ICommentRepository
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: ICommentRepository
) : CommentService {

}