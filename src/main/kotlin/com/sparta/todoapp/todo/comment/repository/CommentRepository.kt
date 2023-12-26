package com.sparta.todoapp.todo.comment.repository

class CommentRepository(
    private val commentEntityRepository: CommentEntityRepository
): ICommentRepository {
}