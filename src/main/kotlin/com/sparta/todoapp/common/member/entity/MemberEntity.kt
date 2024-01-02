package com.sparta.todoapp.common.member.entity

import com.sparta.todoapp.todo.board.entity.TodoBoardEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
	@Column(name = "member_id", unique = true, nullable = false)
	var memberId: String? = null,

	@Column(name = "password", nullable = false)
	var password: String? = null,

	@Column(name = "key", nullable = false)
	var key: String? = null,

	@OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private val cardList: MutableList<TodoCardEntity> = mutableListOf(),

	@OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	val boardList: MutableList<TodoBoardEntity> = mutableListOf()
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

	fun addCard(card: TodoCardEntity) = cardList.add(card)
	fun addBoard(board: TodoBoardEntity) = boardList.add(board)
}