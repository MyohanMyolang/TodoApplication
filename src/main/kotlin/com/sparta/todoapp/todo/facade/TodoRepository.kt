package com.sparta.todoapp.todo.facade

import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.comment.repository.ICommentRepository

class TodoRepository(
    private val todoBoardRepository: ITodoBoardRepository,
    private val todoCardRepository: ITodoCardRepository,
    private val commentRepository: ICommentRepository
) : ITodoRepository {
    override fun findBoardById(id: Long) = todoBoardRepository.findBoardById(id)
    override fun findCardById(id: Long) = todoCardRepository.findCardById(id)
}