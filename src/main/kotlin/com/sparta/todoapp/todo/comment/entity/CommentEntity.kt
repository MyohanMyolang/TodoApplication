package com.sparta.todoapp.todo.comment.entity

import com.sparta.todoapp.todo.card.entity.TodoCardDetailEntity
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity(
    @Column(name = "todo_card_detail")
    val detailCardId: Long,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "user_password")
    val userPassword: String,

    @Column(name = "description")
    var description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_card_detail", referencedColumnName = "id", insertable = false, updatable = false)
    val todoCardDetail: TodoCardDetailEntity? = null;

    fun toResponseDto() = ResponseCommentDto(
        id = id!!,
        userName = userName,
        description = description
    )
}