package com.sparta.todoapp.todo.facade

import com.sparta.todoapp.todo.entity.TodoBoardEntity

interface ITodoRepository {
    fun findBoardById(id: Long): TodoBoardEntity?
}