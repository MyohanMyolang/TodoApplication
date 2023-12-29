package com.sparta.todoapp.todo.comment.controller

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto
import com.sparta.todoapp.todo.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo/comment")
class CommentController(
	private val commentService: CommentService
) {
	@Operation(summary = "댓글 등록", description = "CardId를 통하여 댓글을 등록합니다.")
	@ApiResponses(
		value = [
			ApiResponse(responseCode = "201"),
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@PostMapping
	fun addComment(@RequestBody @Valid requestDto: RequestCommentDto) = responseEntity(HttpStatus.CREATED) {
		commentService.addComment(requestDto)
	}

	@Operation(summary = "댓글 수정")
	@ApiResponses(
		value = [
			ApiResponse(),
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "401",
				description = "해당 Comment와 비밀번호가 일치하지 않을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Comment를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@PatchMapping
	fun updateComment(@RequestBody @Valid updateDto: UpdateCommentDto) = responseEntity(HttpStatus.OK) {
		commentService.updateComment(updateDto)
	}

	@Operation(summary = "댓글 삭제")
	@ApiResponses(
		value = [
			ApiResponse(responseCode = "204"),
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "401",
				description = "해당 Comment와 비밀번호가 일치하지 않을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Comment를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@DeleteMapping
	fun deleteComment(@RequestBody @Valid deleteDto: DeleteCommentDto) = responseEntity(HttpStatus.NO_CONTENT) {
		commentService.deleteComment(deleteDto)
	}
}