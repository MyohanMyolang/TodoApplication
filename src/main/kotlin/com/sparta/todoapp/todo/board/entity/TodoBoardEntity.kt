package com.sparta.todoapp.todo.board.entity

import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.board.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.*

@Entity
@Table(name = "todo_board")
class TodoBoardEntity(
	@Column(name = "board_name")
	private var boardName: String,

	@OneToMany(mappedBy = "boardId", cascade = [CascadeType.ALL])
	private val todoCardEntity: List<TodoCardEntity> = mutableListOf()
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private val id: Long? = null

	@Column(name = "owner")
	val owner: Long? = null


	fun toResponseDto(): ResponseTodoBoardDto = ResponseTodoBoardDto(id = this.id!!, ownerName = this.boardName)

	companion object {
		fun from(domain: TodoBoard) = TodoBoardEntity(boardName = domain.boardName)
		fun from(reqDto: RequestTodoBoardDto) = TodoBoardEntity(boardName = reqDto.boardName!!)
	}
}