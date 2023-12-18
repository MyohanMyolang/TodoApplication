package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.TodoCardDto
import com.sparta.todoapp.todo.entity.TodoList
import com.sparta.todoapp.todo.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo")
class TodoController @Autowired constructor(
    private val todoService: TodoService
) {

    @PostMapping("/board")
    fun createTodoBoard(@RequestBody requestTodoBoardDto: RequestTodoBoardDto): ResponseEntity<Boolean>{

        println("owner name = ${requestTodoBoardDto.ownerName}");

        return ResponseEntity.status(HttpStatus.CREATED).body(false);
    }

    @PostMapping
    fun addTodoCard(@RequestBody todoCardDto:TodoCardDto): ResponseEntity<TodoCardDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodoCard(todoCardDto as TodoList))
    }
}