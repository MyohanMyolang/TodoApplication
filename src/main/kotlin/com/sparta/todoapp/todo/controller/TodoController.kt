package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.todo.dto.*
import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.addTodoBoard(requestTodoBoardDto.convertToEntity()));
    }

    @GetMapping("/board")
    fun getBoardAndTodoList(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int
    ): ResponseEntity<List<ResponseTodoBoardDto>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.getTodoBoardList(page, size));
    }

    // --Card---------------------------------------------------------------------------------------------------

    @PostMapping("/card")
    fun addTodoCard(@RequestBody @Valid requestTodoCardDto: RequestTodoCardDto): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.addTodoCard(requestTodoCardDto.convertToEntity()))
    }

    /**
     * NOTE: Board Update의 경우 Map으로 받아온 후, Entity에 있는 updateData를 통하여 그에 맞는 value를 할당 시킨다.
     */
    @PatchMapping("/card/{id}")
    fun updateTodoCardById(
        @PathVariable id: Long,
        @RequestBody updateData: Map<String, Any>
    ): ResponseEntity<ResponseTodoCardDetailDto> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.updateTodoCardById(id, updateData));
    }

    @DeleteMapping("/card")
    fun deleteCardById() {

    }
}