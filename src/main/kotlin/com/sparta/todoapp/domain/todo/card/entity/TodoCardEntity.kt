package com.sparta.todoapp.domain.todo.card.entity

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.domain.todo.board.entity.TodoBoardEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "todo_card")
class TodoCardEntity(

	val title: String,

	val description: String,

	@ManyToOne(fetch = FetchType.LAZY)
	val writer: MemberEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	val todoBoard: TodoBoardEntity
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null;

	@CreatedDate
	val createdAt: LocalDateTime = LocalDateTime.now()

	val isCompleted: Boolean = false
}