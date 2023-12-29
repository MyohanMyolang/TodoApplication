package com.sparta.todoapp.todo.card.entity

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.board.entity.TodoBoardEntity
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "todo_card")
class TodoCardEntity(
	@Column(name = "board")
	val boardId: Long,

	@Column(name = "title")
	var title: String,

	@Column(name = "isCompleted")
	var isCompleted: Boolean = false,

	@OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
	@JoinColumn(name = "detail")
	val todoCardDetailEntity: TodoCardDetailEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
	var owner: MemberEntity
) {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board", referencedColumnName = "id", insertable = false, updatable = false)
	val board: TodoBoardEntity? = null

	@Column(name = "date")
	private val date: LocalDateTime = LocalDateTime.now()

	fun toResponseDto() = ResponseTodoCardDto(
		id = id!!,
		title = title,
		isCompleted = isCompleted,
		date = date
	)

	fun toDetailResponseDto() = ResponseTodoCardDetailDto(
		id = id!!,
		title = title,
		isCompleted = isCompleted,
		date = date,
		writer = todoCardDetailEntity.writer,
		description = todoCardDetailEntity.description
	)
}