package com.sparta.todoapp.todo.entity

import jakarta.persistence.*


@Entity
class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id:Long? = null;
}