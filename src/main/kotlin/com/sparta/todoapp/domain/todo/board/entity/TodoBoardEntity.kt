package com.sparta.todoapp.domain.todo.board.entity

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardReq
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardRes
import jakarta.persistence.*

@Entity
@Table(name = "TodoBoard")
class TodoBoardEntity(
	@Column
	val boardName: String,

	@ManyToOne(fetch = FetchType.LAZY)
	val owner: MemberEntity,
) {
	fun toResponse() =
		TodoBoardRes(
			boardName = boardName
		)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

	companion object {
		fun of(dto: TodoBoardReq, currentMember: MemberEntity) = TodoBoardEntity(
			boardName = dto.boardName!!,
			owner = currentMember
		)
	}
}