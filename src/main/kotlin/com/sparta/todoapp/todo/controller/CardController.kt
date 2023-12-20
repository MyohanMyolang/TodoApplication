package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.dto.*
import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.service.CardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo/card")
class CardController @Autowired constructor(
    private val todoService: CardService
) {
    @Operation(summary = "Card 등록하기", description = "Card를 등록시킨다. 등록시키기 이전에 board가 필요하다.")
    @PostMapping
    fun addTodoCard(@RequestBody @Valid requestTodoCardDto: RequestTodoCardDto): ResponseEntity<ResponseTodoCardDto> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(todoService.addTodoCard(TodoCard.convertToEntity(requestTodoCardDto)))
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = [
        Content(mediaType = "applicatino/json", schemaProperties = [
            SchemaProperty(name = "title", schema = Schema(implementation = String::class, required = false)),
            SchemaProperty(name = "description", schema = Schema(implementation = String::class, required = false)),
            SchemaProperty(name = "writer", schema = Schema(implementation = String::class, required = false))
        ])
    ])
    @ApiResponses(value = [
        ApiResponse(responseCode = "400", description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n", content = [
            Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))
        ])
    ])
    @PatchMapping("/{id}")
    fun updateTodoCardById(
        @Parameter(description = "Board의 Id를 입력하여 주십시오.") @PathVariable id: Long,
        @RequestBody updateData: Map<String, Any>
    ): ResponseEntity<ResponseTodoCardDetailDto> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.updateTodoCardById(id, updateData));
    }

    @DeleteMapping("/{id}")
    fun deleteTodoCardById(@PathVariable id: Long): ResponseEntity<ResponseTodoCardDetailDto> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(todoService.deleteTodoCardById(id))
    }
}