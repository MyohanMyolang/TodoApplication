package com.sparta.todoapp.todo.card.entity

import jakarta.persistence.*

@Entity
class TodoCardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "writer")
    var writer: String = "";

    @Column(name = "description")
    var description: String = "";


    constructor(descript: String, writer: String) {
        this.writer = writer;
        this.description = descript;
    }

    fun updateValue(key: String, updateData: Any?) {
        when (key) {
            "writer" -> writer = updateData as String;
            "description" -> description = updateData as String;
            else -> throw TODO("BadUpdateRequestException - [key]는 존재하지 않습니다.")
        }
    }
}
