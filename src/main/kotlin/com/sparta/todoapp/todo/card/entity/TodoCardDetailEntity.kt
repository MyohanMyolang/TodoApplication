package com.sparta.todoapp.todo.card.entity

import com.sparta.todoapp.todo.comment.entity.CommentEntity
import jakarta.persistence.*

@Entity
@Table(name = "todo_card_detail")
class TodoCardDetailEntity(
	@Column(name = "description")
	var description: String,

	@Column(name = "writer")
	var writer: String
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private val id: Long? = null

	@OneToMany(mappedBy = "todoCardDetail", cascade = [CascadeType.REMOVE])
	private val commentList: List<CommentEntity> = mutableListOf()

}
