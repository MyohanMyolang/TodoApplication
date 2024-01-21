package com.sparta.todoapp.domain.todo.comment.entity

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.domain.todo.card.entity.TodoCardEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class CommentEntity(



	@ManyToOne(fetch = FetchType.LAZY)
	val writer: MemberEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	val card: TodoCardEntity
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

	@CreatedDate
	val createdAt: LocalDateTime = LocalDateTime.now()
}