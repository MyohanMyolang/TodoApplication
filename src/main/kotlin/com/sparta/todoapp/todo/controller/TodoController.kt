package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.entity.TodoList
import com.sparta.todoapp.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * NOTE: 2023-12-18 21:04:42
 * TODO:
 *  + Board Create만 만들기
 *  + TodoList curd 만들기
 *  + Swagger 적용 시키기. - 최후 순위
 *
 */

@RestController
@RequestMapping("/todo")
class TodoController @Autowired constructor(
    private val todoService: TodoService
) {

    @PostMapping("/board")
            /**
             * Board 만들기 요청
             * @param requestBoardDto board정보
             * @return 등록 된 이후의 board의 정보를 반환한다.
             */
    fun createTodoBoard(@RequestBody @Valid requestTodoBoardDto: RequestTodoBoardDto): ResponseEntity<ResponseTodoBoardDto> {

        val todoBoard = todoService.addTodoBoard(requestTodoBoardDto.convertToEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body(todoBoard.convertToResponseDto());
    }

    @GetMapping("/board/{page}")
    fun getBoardAndTodoList(){

    }

    @PostMapping
    fun addTodoCard(@RequestBody requestTodoCardDto: RequestTodoCardDto): ResponseEntity<RequestTodoCardDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodoCard(requestTodoCardDto as TodoList))
    }

    /**
     * NOTE: Board Update의 경우 Map으로 받아온 후, Entity에 있는 updateData를 통하여 그에 맞는 value를 할당 시킨다.
     */
}