package com.sparta.todoapp.todo.comment.config

import com.sparta.todoapp.todo.comment.repository.CommentEntityRepository
import com.sparta.todoapp.todo.comment.repository.CommentRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommentConfig {
    @Bean
    fun commentRepositoryConfig(
        commentEntityRepository: CommentEntityRepository
    ) = CommentRepository(commentEntityRepository)
}