package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.facade.ITodoRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
    private val auth: IAuth,
    private val todoCardRepository: ITodoCardRepository,
    private val todoRepository: ITodoRepository
) : CardService {

    fun <T> authCheck(func: (it: Any) -> T): T {
        return func.invoke("test")
    }


    /**
     * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
     */
    @Transactional
    override fun addTodoCard(requestTodoCard: RequestTodoCardDto): ResponseTodoCardDetailDto {
        todoRepository.findBoardById(requestTodoCard.boardId!!) ?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

        return todoCardRepository.addCard(TodoCard.from(requestTodoCard)).toDetailResponseDto()
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun updateTodoCardById(id: Long, updateData: UpdateTodoCardDto): ResponseTodoCardDetailDto {
        val findCard = todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

        return todoCardRepository.updateDataByDto(findCard, updateData).toDetailResponseDto()
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
        val findCard = todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

        return todoCardRepository.deleteCard(findCard).toDetailResponseDto()
    }

    @Transactional
    override fun getSortedCardList(id: Long, page: Int, size: Int, sort: String): List<ResponseTodoCardDto> {
        val responseDtoList = mutableListOf<ResponseTodoCardDto>()
        when (sort) {
            "desc" -> todoCardRepository.getCardListDescByBoardId(id, page, size)
            "asc" -> todoCardRepository.getCardListAscByBoardId(id, page, size)
            else -> throw NotFoundTargetException("해당 정렬 방식은 존재하지 않습니다.")
        }.forEach { responseDtoList.add(it.toResponseDto()) }

        return responseDtoList
    }

    @Transactional
    override fun completedChange(id: Long): Boolean {
        val findCard = todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

        return todoCardRepository.completedChange(findCard)
    }
}