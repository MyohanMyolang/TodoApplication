package com.sparta.todoapp.todo.board.entity

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.*

@Entity
@Table(name = "todo_board")
class TodoBoardEntity(
	@Column(name = "board_name")
	private var boardName: String,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
	var owner: MemberEntity
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private val id: Long? = null

	@OneToMany(mappedBy = "board", cascade = [CascadeType.REMOVE])
	val todoCardEntity: List<TodoCardEntity> = mutableListOf()


	fun toResponseDto(): ResponseTodoBoardDto = ResponseTodoBoardDto(id = this.id!!, ownerName = this.boardName)

	companion object {
		fun from(domain: TodoBoard, owner: MemberEntity) = TodoBoardEntity(boardName = domain.boardName, owner = owner)
	}
}