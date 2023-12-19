package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.entity.TodoCard
import org.springframework.stereotype.Service

@Service
interface TodoService {
    fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto
    fun addTodoBoard(todoBoard: TodoBoard): ResponseTodoBoardDto;

    /**
     * size는 요청할 때 고정으로 보내야 중복 없이 전달 된다.
     * @param page 해당 페이지
     * @param size 가져올 BoardList의 숫자
     * @return List<ResponseTodoBoardDto>
     */
    fun getTodoBoardList(page: Int, size: Int): List<ResponseTodoBoardDto>

    fun updateTodoCardById(id: Long, updateData: Map<String, Any>): ResponseTodoCardDetailDto
    fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto

}