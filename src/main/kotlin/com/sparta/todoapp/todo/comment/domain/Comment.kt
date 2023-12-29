package com.sparta.todoapp.todo.comment.domain

import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto
import com.sparta.todoapp.todo.comment.entity.CommentEntity

class Comment(
	val cardId: Long,
	val userName: String,
	val userPassword: String,
	val description: String
) {
	companion object {
		fun from(dto: RequestCommentDto) = Comment(
			cardId = dto.cardId!!,
			userName = dto.userName!!,
			userPassword = dto.userPassword!!,
			description = dto.description!!
		)

		fun from(dto: DeleteCommentDto) = Comment(
			cardId = 0L,
			userName = dto.userName!!,
			userPassword = dto.userPassword!!,
			description = ""
		)

		fun of(dto: UpdateCommentDto, cardId: Long) = Comment(
			cardId = cardId,
			userName = dto.userName!!,
			userPassword = dto.userPassword!!,
			description = dto.description!!
		)
	}

	fun toEntity() = CommentEntity(
		cardDetailId = cardId,
		userName = userName,
		userPassword = userPassword,
		description = description
	)

	fun checkAuth(entity: CommentEntity): Boolean = entity.userName == userName && entity.userPassword == userPassword
}