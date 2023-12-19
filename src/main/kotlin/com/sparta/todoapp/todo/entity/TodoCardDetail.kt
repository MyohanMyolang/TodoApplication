package com.sparta.todoapp.todo.entity

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
class TodoCardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "writer")
    var writer: String = "";

    @Column(name = "descript")
    var descript: String = "";


    constructor(descript: String, writer: String) {
        this.writer = writer;
        this.descript = descript;
    }

    fun updateValue(key: String, updateData: Any) {
        when (key) {
            "writer" -> writer = updateData as String;
            "descript" -> descript = updateData as String;
            else -> throw TODO("BadUpdateRequestException 정의 후 it의 정보 전달")
        }
    }
}
