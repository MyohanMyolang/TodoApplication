package com.sparta.todoapp.todo.board.config

import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.board.repository.TodoBoardEntityRepository
import com.sparta.todoapp.todo.board.repository.TodoBoardPostgresRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BoardConfig {
	@Bean
	fun boardRepositoryConfig(todoBoardEntityRepository: TodoBoardEntityRepository, memberEntityRepository: MemberEntityRepository): ITodoBoardRepository =
		TodoBoardPostgresRepository(todoBoardEntityRepository, memberEntityRepository)
}