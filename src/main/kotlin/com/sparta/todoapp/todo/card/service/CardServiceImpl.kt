package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.*
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.comment.dto.ResponseCommentDto
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

    private fun findTodoCardById(id: Long): TodoCardEntity =
        todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

    /**
     * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
     */
    @Transactional
    override fun addTodoCard(requestTodoCard: RequestTodoCardDto): ResponseTodoCardDetailDto {
        todoRepository.findBoardById(requestTodoCard.boardId!!) ?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

        return todoCardRepository.addCard(TodoCard.from(requestTodoCard)).toDetailResponseDto()
    }

    override fun getTodoCardDetailByIdWithCommentList(id: Long): ResponseTodoCardDetailWithCommentListDto {
        val findCard = findTodoCardById(id)
        val commentListDto = mutableListOf<ResponseCommentDto>()
        val commentList = todoRepository.findCommentListByTodoCardDetailEntity(findCard.todoCardDetailEntity).forEach {
            commentListDto.add(it.toResponseDto())
        }
        return ResponseTodoCardDetailWithCommentListDto(findCard.toDetailResponseDto(), commentListDto)
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun updateTodoCardById(id: Long, updateData: UpdateTodoCardDto): ResponseTodoCardDetailDto {
        val findCard = findTodoCardById(id)

        return todoCardRepository.updateDataByDto(findCard, updateData).toDetailResponseDto()
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
        val findCard = findTodoCardById(id)

        return todoCardRepository.deleteCard(findCard).toDetailResponseDto()
    }

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
        val findCard = findTodoCardById(id)

        return todoCardRepository.completedChange(findCard)
    }
}