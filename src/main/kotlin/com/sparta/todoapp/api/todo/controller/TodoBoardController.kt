package com.sparta.todoapp.api.todo.controller

import com.sparta.todoapp.api.todo.service.TodoBoardApiService
import com.sparta.todoapp.domain.todo.board.dto.TodoBoardReq
import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todo/board")
class TodoBoardController(
	private val todoBoardApiService: TodoBoardApiService
) {

	@PostMapping
	fun addTodoBoard(dto: TodoBoardReq, authentication: Authentication) = responseEntity(HttpStatus.CREATED){
		/**
		 * TODO:
		 *  1. SecurityContextHolder에서 Context를 가져오기.
		 *  2. 가져온 후 Principal 객채를 가져와 MemberEntity를 가져 온다.
		 *  3. TodoBoardApiService ADD 요청하기.
		 */
		val userPrincipal = authentication.principal as UserPrincipal
		println(userPrincipal.member.memberId)
	}

}