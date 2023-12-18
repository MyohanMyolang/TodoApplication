package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.entity.TodoList
import com.sparta.todoapp.todo.repository.TodoBoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl @Autowired constructor(
    private val todoBoardRepository: TodoBoardRepository
): TodoService {
    override fun addTodoCard(todoList: TodoList): RequestTodoCardDto {
        TODO("Not yet implemented")
    }

    override fun addTodoBoard(todoBoard: TodoBoard): TodoBoard {
        todoBoardRepository.save(todoBoard);
        return todoBoard;
    }
}