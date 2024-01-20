package com.sparta.todoapp.domain.todo.board.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy::class)
class TodoBoardListRes(
	val todoBoardList: List<TodoBoardRes>
)