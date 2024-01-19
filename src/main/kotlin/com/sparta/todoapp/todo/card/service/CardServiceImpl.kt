package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.card.dto.UpdateTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
	private val auth: IAuth,
	private val todoCardRepository: ITodoCardRepository,
	private val todoBoardRepository: ITodoBoardRepository
) : CardService {


	override fun getTodoCardById(id: Long): TodoCardEntity =
		todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Card가 존재하지 않습니다.")

	/**
	 * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
	 */
	@Transactional
	override fun addTodoCard(requestTodoCard: RequestTodoCardDto): ResponseTodoCardDetailDto {
		val owner = todoBoardRepository.findBoardById(requestTodoCard.boardId!!)?.owner
			?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

		return auth.checkPermission(owner) {
			todoCardRepository.addCard(TodoCard.from(requestTodoCard), owner)
				.toDetailResponseDto()
		}
	}

	override fun getTodoCardDetailById(id: Long): ResponseTodoCardDetailDto =
		getTodoCardById(id).toDetailResponseDto()

	/**
	 * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
	 */
	@Transactional
	override fun updateTodoCardById(id: Long, updateData: UpdateTodoCardDto): ResponseTodoCardDetailDto {
		val findCard = getTodoCardById(id)

		return auth.checkPermission(findCard.owner) {
			todoCardRepository.updateDataByDto(findCard, updateData).toDetailResponseDto()
		}
	}

	/**
	 * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
	 */
	@Transactional
	override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
		val findCard = getTodoCardById(id)

		return auth.checkPermission(findCard.owner) {
			todoCardRepository.deleteCard(findCard).toDetailResponseDto()
		}
	}

	override fun getSortedCardList(boardId: Long, page: Int, size: Int, sort: String): List<ResponseTodoCardDto> {
		val responseDtoList = mutableListOf<ResponseTodoCardDto>()
		when (sort) {
			"desc" -> todoCardRepository.getCardListDescByBoardId(boardId, page, size)
			"asc" -> todoCardRepository.getCardListAscByBoardId(boardId, page, size)
			else -> throw Exception("이 에러가 발생할 경우 문의 주십시오.")
		}.forEach {
			responseDtoList.add(it.toResponseDto())
		}

		return responseDtoList
	}

	@Transactional
	override fun completedChange(id: Long): Boolean {
		val findCard = getTodoCardById(id)

		return auth.checkPermission(findCard.owner) { todoCardRepository.completedChange(findCard) }
	}
}