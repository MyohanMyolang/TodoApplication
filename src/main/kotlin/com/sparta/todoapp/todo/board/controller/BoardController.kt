package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.service.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * TODO:
 *  1. Controller에 로그인 확인 로직을 추가한다.
 *  2.
 */

@RestController
@Validated
@RequestMapping("/todo/board")
class BoardController(
    private val boardService: BoardService
) {
    @Operation(summary = "Board 등록", description = "새로운 Board를 생성합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "생성 완료"),
        ApiResponse(
            responseCode = "400", description = "Validation을 통과하지 못하였을 경우 실패한 부분만 key와 실패 사유가 나오게 됩니다.", content = [
                Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))
            ]
        ),
        ApiResponse(
            responseCode = "401", description = "로그인 되어 있지 않을 때 발생합니다.", content = [
                Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))
            ]
        ),
        ApiResponse(
            responseCode = "404", description = "로그인 상태가 정상적이지 않을 때 발생합니다.", content = [
                Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class))
            ]
        )
    )
    @PostMapping
    fun addTodoBoard(@RequestBody @Valid requestTodoBoardDto: RequestTodoBoardDto) =
        responseEntity(HttpStatus.CREATED) {
            boardService.addTodoBoard(requestTodoBoardDto)
        }

    @Operation(summary = "Board 리스트 가져오기", description = "page와 size에 해당하는 보드들을 가지고 오게 됩니다. 추가적으로 page전체 크기를 가져옵니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "가져오기 완료"),
        ApiResponse(
            responseCode = "404", description = "page 범위 밖일 때 발생합니다.", content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(description = "페이지가 존재하지 않습니다.", implementation = ErrorObject::class)
                )
            ]
        )
    )
    @GetMapping
    fun getTodoBoardList(
        @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = "page의 최소값은 1입니다.") page: Int,
        @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = "size의 최소값은 1입니다.") size: Int,
    ) = responseEntity(HttpStatus.OK) {
        boardService.getTodoBoardList(page, size)
    }

    @Operation(summary = "이름으로 보드 가져오기")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "가져오기 완료"),
        ApiResponse(
            responseCode = "404", description = "해당 이름을 가진 Board가 존재하지 않을 때 발생합니다.", content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(description = "해당하는 Board가 존재하지 않습니다.", implementation = ErrorObject::class)
                )
            ]
        )
    )
    @GetMapping("/search/{name}")
    fun getTodoBoardByName(@PathVariable name: String) = responseEntity(HttpStatus.OK) {
        boardService.getBoardListByName(name)
    }
}