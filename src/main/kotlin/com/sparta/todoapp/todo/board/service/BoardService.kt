package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity
import org.springframework.stereotype.Service

@Service
interface BoardService {
    fun addTodoBoard(todoBoard: TodoBoardEntity): ResponseTodoBoardDto;

    fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto
}