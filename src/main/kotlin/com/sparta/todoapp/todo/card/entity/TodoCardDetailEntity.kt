package com.sparta.todoapp.todo.card.entity

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
    private val id: Long? = null;


    fun updateValue(key: String, updateData: Any?) {
        when (key) {
            "writer" -> writer = updateData as String;
            "description" -> description = updateData as String;
            else -> throw TODO("BadUpdateRequestException - [key]는 존재하지 않습니다.")
        }
    }
}
