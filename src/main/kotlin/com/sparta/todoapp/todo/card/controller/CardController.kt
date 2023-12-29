package com.sparta.todoapp.todo.card.controller

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailWithCommentListDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardWithCommentListDto
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.service.CardService
import com.sparta.todoapp.todo.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("/todo/card")
class CardController(
	private val cardService: CardService, private val commentService: CommentService
) {
	@Operation(summary = "Card 등록하기", description = "Card를 등록시킨다. 등록시키기 이전에 board가 필요하다.")
	@ApiResponses(
		value = [ApiResponse(
			responseCode = "201", description = "카드 등록에 성공하였을 때 발생합니다."
		), ApiResponse(
			responseCode = "400",
			description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		), ApiResponse(
			responseCode = "401",
			description = "로그인 되지 않았을 때 발생합니다.",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		), ApiResponse(
			responseCode = "403",
			description = "해당 유저의 Board가 아닐 때 발생합니다.",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		), ApiResponse(
			responseCode = "404",
			description = "board를 찾지 못하였을 때 발생합니다.",
			content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
		)]
	)
	@PostMapping
	fun addTodoCard(@RequestBody @Valid requestTodoCardDto: RequestTodoCardDto) = responseEntity(HttpStatus.CREATED) {
		cardService.addTodoCard(requestTodoCardDto)
	}

	@Operation(summary = "Card 수정")
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "401",
				description = "로그인 되지 않았을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "403",
				description = "해당 유저의 Board가 아닐 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Card를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@PatchMapping("/{id}")
	fun updateTodoCardById(
		@PathVariable id: Long, @RequestBody @Valid updateData: UpdateTodoCardDto
	) = responseEntity(HttpStatus.OK) {
		cardService.updateTodoCardById(id, updateData)
	}

	@Operation(summary = "Card 삭제")
	@ApiResponses(
		value = [
			ApiResponse(responseCode = "204", description = "Card 삭제 성공시 발생합니다. 삭제 된 Card의 정보를 반환합니다."),
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "401",
				description = "로그인 되지 않았을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "403",
				description = "해당 유저의 Board가 아닐 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Card를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@DeleteMapping("/{id}")
	fun deleteTodoCardById(@PathVariable id: Long) = responseEntity(HttpStatus.NO_CONTENT) {
		cardService.deleteTodoCardById(id)
	}

	@Operation(summary = "Card 목록", description = "Board의 Id를 받아 Card들을 가져옵니다. 각 Card의 CommentList와 같이 가져옵니다.")
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@GetMapping
	fun getTodoCardListByIdWithCommentList(
		@RequestParam boardId: Long,
		@RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = "page의 최소값은 1입니다.") page: Int,
		@RequestParam(required = false, defaultValue = "desc") @Pattern(
			regexp = "^(desc|asc)$", message = "sort는 desc 또는 asc 둘 중 하나의 값을 가져야 합니다."
		) sort: String
	) = responseEntity(HttpStatus.OK) {
		val resultList = mutableListOf<ResponseTodoCardWithCommentListDto>()
		cardService.getSortedCardList(boardId, page, 5, sort).forEach {
			val commentList = commentService.getCommentListByCardId(it.id)
			resultList.add(ResponseTodoCardWithCommentListDto(it, commentList))
		}
		resultList
	}

	@Operation(summary = "Card Detail 조회", description = "Card의 Id를 받아 조회합니다. CommentList와 같이 가져옵니다.")
	@ApiResponses(
		value = [
			ApiResponse(),
			ApiResponse(
				responseCode = "400",
				description = "key가 잘못되거나 validation이 잘못되었을 때 발생합니다.\n",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Card를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@GetMapping("/{id}")
	fun getTodoCardDetailByIdWithCommentList(@PathVariable id: Long) = responseEntity(HttpStatus.OK) {
		val cardDetail = cardService.getTodoCardDetailById(id)
		val commentList = commentService.getCommentListByCardId(id)

		ResponseTodoCardDetailWithCommentListDto(cardDetail, commentList)
	}

	@Operation(summary = "Card의 할일 완료 상태를 변경합니다.", description = "Card Id를 받아와 상태를 바꿉니다. 바뀐 상태를 반환합니다.")
	@ApiResponses(
		value = [
			ApiResponse(
				responseCode = "401",
				description = "로그인 되지 않았을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "403",
				description = "해당 유저의 Board가 아닐 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
			ApiResponse(
				responseCode = "404",
				description = "해당 Card를 찾지 못했을 때 발생합니다.",
				content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))]
			),
		]
	)
	@GetMapping("/completed/{id}")
	fun cardCompletedChange(@PathVariable id: Long) = responseEntity(HttpStatus.OK) {
		cardService.completedChange(id)
	}
}