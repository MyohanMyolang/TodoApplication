package com.sparta.todoapp.todo.board.config

import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.board.repository.TodoBoardPostgresRepository
import com.sparta.todoapp.todo.facade.TodoBoardEntityRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BoardConfig {
    @Bean
    fun boardRepositoryConfig(todoBoardEntityRepository: TodoBoardEntityRepository): ITodoBoardRepository =
        TodoBoardPostgresRepository(todoBoardEntityRepository)
}