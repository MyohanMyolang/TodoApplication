package com.sparta.todoapp.todo.card.controller

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.service.CardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo/card")
class CardController(
    private val todoService: CardService
) {
    @Operation(summary = "Card 등록하기", description = "Card를 등록시킨다. 등록시키기 이전에 board가 필요하다.")
    @PostMapping
    fun addTodoCard(@RequestBody @Valid requestTodoCardDto: RequestTodoCardDto) = responseEntity(HttpStatus.CREATED) {
        todoService.addTodoCard(requestTodoCardDto)
    }

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "400", description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n", content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))
                ]
            )
        ]
    )
    @PatchMapping("/{id}")
    fun updateTodoCardById(
        @Parameter(description = "Board의 Id를 입력하여 주십시오.") @PathVariable id: Long,
        @RequestBody @Valid updateData: UpdateTodoCardDto
    ) = responseEntity(HttpStatus.OK) {
        todoService.updateTodoCardById(id, updateData)
    }

    @DeleteMapping("/{id}")
    fun deleteTodoCardById(@PathVariable id: Long) = responseEntity(HttpStatus.NO_CONTENT) {
        todoService.deleteTodoCardById(id)
    }

    @GetMapping
    fun getTodoCardListById(
        @RequestParam boardId: Long,
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "desc") sort: String
    ) = responseEntity(HttpStatus.OK) {
        todoService.getSortedCardList(boardId, page, 5, sort)
    }

    @GetMapping("/{id}")
    fun getTodoCardDetailByIdWithCommentList(@PathVariable id: String) = responseEntity(HttpStatus.OK){

    }

    @GetMapping("/completed/{id}")
    fun cardCompletedChange(@PathVariable id: Long) = responseEntity(HttpStatus.OK) {
        todoService.completedChange(id)
    }
}