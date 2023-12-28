package com.sparta.todoapp.todo.board.repository

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.entity.TodoBoardEntity
import org.springframework.data.domain.Page

interface ITodoBoardRepository {
    fun paginationFindAll(page: Int, size: Int): Page<TodoBoardEntity>
    fun findAll(): MutableList<TodoBoardEntity>
    fun addBoard(todoBoard: TodoBoard, owner: MemberEntity): TodoBoardEntity
    fun findBoardById(id: Long): TodoBoardEntity?
    fun findBoardListByName(name: String): List<TodoBoardEntity>
}