package com.sparta.todoapp.api.todo.controller

import com.sparta.todoapp.api.todo.service.TodoService
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardReq
import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todo/board")
@Validated
class TodoBoardController(
	private val todoService: TodoService
) {

	@PostMapping
	fun addTodoBoard(
		@Valid @RequestBody dto: TodoBoardReq,
		authentication: Authentication
	) =
		responseEntity(HttpStatus.CREATED) {
			val userPrincipal = authentication.principal as UserPrincipal
			todoService.addTodoBoard(dto, userPrincipal.member)
		}

}