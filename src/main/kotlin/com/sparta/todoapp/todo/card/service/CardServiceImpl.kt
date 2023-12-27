package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.*
import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import com.sparta.todoapp.todo.card.repository.ITodoCardRepository
import com.sparta.todoapp.todo.comment.service.CommentService
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import com.sparta.todoapp.todo.service.BoardService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
    private val auth: IAuth,
    private val todoCardRepository: ITodoCardRepository,
    private val todoBoardService: BoardService,
    private val commentService: CommentService
) : CardService {


    override fun getTodoCardById(id: Long): TodoCardEntity =
        todoCardRepository.findCardById(id) ?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

    /**
     * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
     */
    @Transactional
    override fun addTodoCard(requestTodoCard: RequestTodoCardDto): ResponseTodoCardDetailDto {
        val owner = todoBoardService.getBoardById(requestTodoCard.boardId!!).owner

        return auth.checkAuth(owner) {
            todoCardRepository.addCard(TodoCard.from(requestTodoCard), auth.getCurrentMemberEntity())
                .toDetailResponseDto()
        }
    }

    override fun getTodoCardDetailByIdWithCommentList(id: Long): ResponseTodoCardDetailWithCommentListDto {
        val findCard = getTodoCardById(id)
        val commentListDto = commentService.getCommentListByCardId(id)
        return ResponseTodoCardDetailWithCommentListDto(findCard.toDetailResponseDto(), commentListDto)
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun updateTodoCardById(id: Long, updateData: UpdateTodoCardDto): ResponseTodoCardDetailDto {
        val findCard = getTodoCardById(id)

        return auth.checkAuth(findCard.owner) {
            todoCardRepository.updateDataByDto(findCard, updateData).toDetailResponseDto()
        }
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    @Transactional
    override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
        val findCard = getTodoCardById(id)

        return auth.checkAuth(findCard.owner) {
            todoCardRepository.deleteCard(findCard).toDetailResponseDto()
        }
    }

    override fun getSortedCardList(id: Long, page: Int, size: Int, sort: String): List<ResponseTodoCardWithCommentListDto> {
        val responseDtoList = mutableListOf<ResponseTodoCardWithCommentListDto>()
        when (sort) {
            "desc" -> todoCardRepository.getCardListDescByBoardId(id, page, size)
            "asc" -> todoCardRepository.getCardListAscByBoardId(id, page, size)
            else -> throw NotFoundTargetException("해당 $sort 정렬 방식은 존재하지 않습니다.")
        }.forEach {
            responseDtoList.add(
                ResponseTodoCardWithCommentListDto(it.toResponseDto(), commentService.getCommentListByCardId(it.id!!))
            )
        }

        return responseDtoList
    }

    @Transactional
    override fun completedChange(id: Long): Boolean {
        val findCard = getTodoCardById(id)

        return auth.checkAuth(findCard.owner) { todoCardRepository.completedChange(findCard) }
    }
}