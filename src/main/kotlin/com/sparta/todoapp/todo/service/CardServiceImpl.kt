package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.entity.TodoCard
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.repository.TodoCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardServiceImpl @Autowired constructor(
    private val todoCardRepository: TodoCardRepository
) : CardService {

    /**
     * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
     */
    override fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto {
        try {
            return todoCardRepository.save(todoCard).convertDto(); } catch (e: DataIntegrityViolationException) {
            throw NotFoundTargetException("해당 Board가 존재하지 않습니다.")
        }
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    override fun updateTodoCardById(id: Long, updateData: Map<String, Any>): ResponseTodoCardDetailDto {
        val foundCard = todoCardRepository.findByIdOrNull(id) ?: throw NotFoundTargetException("해당 Todo Card가 존재하지 않습니다.")
        return todoCardRepository.save(foundCard.updateValue(updateData)).convertDetailDto()
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
        val foundCard = todoCardRepository.findByIdOrNull(id) ?: throw NotFoundTargetException("해당 Todo Card가 존재하지 않습니다.")
        todoCardRepository.delete(foundCard);
        return foundCard.convertDetailDto()
    }
}
