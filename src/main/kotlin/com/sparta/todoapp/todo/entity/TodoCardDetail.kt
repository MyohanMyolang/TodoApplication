package com.sparta.todoapp.todo.entity

import jakarta.persistence.*
import java.time.LocalDateTime

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
}