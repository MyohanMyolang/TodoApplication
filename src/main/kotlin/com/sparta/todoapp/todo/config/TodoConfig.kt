package com.sparta.todoapp.todo.config

import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.comment.repository.CommentRepository
import com.sparta.todoapp.todo.facade.ITodoRepository
import com.sparta.todoapp.todo.facade.TodoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TodoConfig {
    @Bean
    fun todoRepositoryConfig(
        todoBoardRepository: ITodoBoardRepository,
        todoCardRepository: ITodoCardRepository,
        commentRepository: CommentRepository
    ): ITodoRepository = TodoRepository(
        todoBoardRepository = todoBoardRepository,
        todoCardRepository = todoCardRepository,
        commentRepository = commentRepository,
    )
}