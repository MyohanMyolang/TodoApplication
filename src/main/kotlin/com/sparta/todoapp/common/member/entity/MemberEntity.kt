package com.sparta.todoapp.common.member.entity

import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(
	@Column(name = "member_id", unique = true)
	var memberId: String,

	@Column(name = "password", nullable = false)
	var password: String,

	@Column(name = "key", nullable = false)
	var key: String,

	@OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
	private val cardList: List<TodoCardEntity> = mutableListOf(),

	@OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
	private val boardList: List<TodoCardEntity> = mutableListOf()
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}