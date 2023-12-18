package com.sparta.todoapp.todo.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TodoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id:Long? = null;

    @Column(name = "owner_name")
    private var ownerName: String = "";
}