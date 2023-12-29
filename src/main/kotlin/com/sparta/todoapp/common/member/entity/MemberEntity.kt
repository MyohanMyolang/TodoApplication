package com.sparta.todoapp.common.member.entity

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
	var key: String? = null
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null


	@OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
	val cardList: List<TodoCardEntity> = mutableListOf()

	@OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
	val boardList: List<TodoCardEntity> = mutableListOf()
}