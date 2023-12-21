package com.sparta.todoapp.todo.controller

import com.sparta.todoapp.system.error.ErrorObject
import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.service.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * TODO:
 *  1. Controller에 로그인 확인 로직을 추가한다.
 *  2.
 */

@RestController
@RequestMapping("/todo/board")
class BoardController @Autowired constructor(
    private val boardService: BoardService
) {
    @Operation(summary = "Board 등록", description = "새로운 Board를 생성합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "생성 완료"),
        ApiResponse(
            responseCode = "400", description = "Validation을 통과하지 못하였을 경우 실패한 부분만 key와 실패 사유가 나오게 됩니다.", content = [
                (Content(mediaType = "application/json", schema = Schema(implementation = ErrorObject::class)))
            ]
        )
    )
    @PostMapping
    fun createTodoBoard(@RequestBody @Valid requestTodoBoardDto: RequestTodoBoardDto): ResponseEntity<ResponseTodoBoardDto> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(boardService.addTodoBoard(TodoBoard.convertToEntity(requestTodoBoardDto)));
    }

    @Operation(summary = "Board 리스트 가져오기", description = "page와 size에 해당하는 보드들을 가지고 오게 됩니다. 추가적으로 page전체 크기를 가져옵니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "가져오기 완료"),
        ApiResponse(
            responseCode = "404", description = "page 범위 밖일 때 발생합니다.", content = [
                (Content(
                    mediaType = "application/json",
                    schema = Schema(description = "페이지가 존재하지 않습니다.", implementation = ErrorObject::class)
                ))
            ]
        )
    )
    @GetMapping
    fun getTodoBoardList(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @Parameter(description = "이후 page가 변경되어도 size값은 유지되어야 중복 데이터를 가져오지 않습니다.") @RequestParam(
            required = false,
            defaultValue = "10"
        ) size: Int,
        @Parameter(description = "소문자로 작성하여 주십시오.") @RequestParam(required = false, defaultValue = "desc") sort: String
    ): ResponseEntity<ResponseTodoBoardWithPageDto> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(boardService.getTodoBoardList(page, size, sort));
    }

    @GetMapping("/search/{name}")
    fun getTodoBoardByName(@PathVariable name: String) {
        TODO("이름 기반으로 board검색해서 반환하기")
    }
}