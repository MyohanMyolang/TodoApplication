package com.sparta.todoapp.todo.comment.entity

import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity(
    @Column(name = "todo_card")
    val cardId: Long,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "user_password")
    val userPassword: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_card", referencedColumnName = "id", insertable = false, updatable = false)
    val todoCard: TodoCardEntity? = null;
}