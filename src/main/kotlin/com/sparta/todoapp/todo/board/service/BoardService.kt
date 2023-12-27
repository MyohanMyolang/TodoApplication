package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity
import org.springframework.stereotype.Service

interface BoardService {
    fun addTodoBoard(requestTodoBoardDto: RequestTodoBoardDto): ResponseTodoBoardDto;

    fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto
    fun getBoardById(id: Long): TodoBoardEntity
}