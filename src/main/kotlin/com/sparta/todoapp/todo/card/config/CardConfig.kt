package com.sparta.todoapp.todo.card.config

import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.card.repository.TodoCardDetailEntityRepository
import com.sparta.todoapp.todo.card.repository.TodoCardEntityRepository
import com.sparta.todoapp.todo.card.repository.TodoCardPostgresRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CardConfig {
	@Bean
	fun todoCardRepositoryConfig(
		cardRepository: TodoCardEntityRepository,
		cardDetailRepository: TodoCardDetailEntityRepository
	): ITodoCardRepository = TodoCardPostgresRepository(cardRepository, cardDetailRepository)
}