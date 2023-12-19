package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.repository.TodoBoardRepository
import com.sparta.todoapp.todo.repository.TodoCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl @Autowired constructor(
    private val todoBoardRepository: TodoBoardRepository,
    private val todoCardRepository: TodoCardRepository
) : TodoService {
    override fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto {
        return todoCardRepository.save(todoCard).convertDto();
    }

    override fun addTodoBoard(todoBoard: TodoBoard): ResponseTodoBoardDto {
        return todoBoardRepository.save(todoBoard).convertToResponseDto();
    }

    override fun getTodoBoardList(page: Int, size: Int): List<ResponseTodoBoardDto> {
        val findAll = todoBoardRepository.findAll(PageRequest.of(page - 1, size))
        val result = mutableListOf<ResponseTodoBoardDto>();
        findAll.forEach { result.add(it.convertToResponseDto()) }
        return result;
    }
}