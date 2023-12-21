package com.sparta.todoapp.todo.card.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCard
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.card.repository.TodoCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardServiceImpl @Autowired constructor(
    private val todoCardRepository: TodoCardRepository,
    private val auth: IAuth
) : CardService {

    fun <T> cardAuthCheck(func: () -> T): T {
        println("=============================================================================\n\n\n")
        println("TODO - 권한 체크 로직")
        /**
         * NOTE:
         *  1. Card의 Owner와 현재 사용자의 Key가 일치하는지 확인한다.
         *  2. 권한이 없다면 NoPermission
         */
        val test = todoCardRepository.findAll()
        if(test.size != 0){
            println(test[0].owner?.key);
        }
        println("\n\n\n=============================================================================")
        return func.invoke();

    }

    /**
     * @throws NotFoundTargetException Board가 존재하지 않으면 던집니다.
     */
    override fun addTodoCard(todoCard: TodoCard): ResponseTodoCardDto = cardAuthCheck{
        // NOTE: board Owner의 key값과 사용자의 Key값이 일치하는지 확인한다.
        try {
            return@cardAuthCheck todoCardRepository.save(todoCard)
                .convertDto(); } catch (e: DataIntegrityViolationException) {
            throw NotFoundTargetException("해당 Board가 존재하지 않습니다.")
        }
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    override fun updateTodoCardById(id: Long, updateData: Map<String, Any>): ResponseTodoCardDetailDto {
        val foundCard =
            todoCardRepository.findByIdOrNull(id) ?: throw NotFoundTargetException("해당 Todo Card가 존재하지 않습니다.")
        return todoCardRepository.save(foundCard.updateValue(updateData)).convertDetailDto()
    }

    /**
     * @throws NotFoundTargetException id와 일치하는 카드가 존재 하지 않을 경우 던집니다.
     */
    override fun deleteTodoCardById(id: Long): ResponseTodoCardDetailDto {
        val foundCard =
            todoCardRepository.findByIdOrNull(id) ?: throw NotFoundTargetException("해당 Todo Card가 존재하지 않습니다.")
        todoCardRepository.delete(foundCard);
        return foundCard.convertDetailDto()
    }

    fun getSortedCardList(id:Long, sort:String){
        when(sort) {
            "asc" -> todoCardRepository.findAllByBoardIdOrderByDateAsc(PageRequest.of(0, 5), id)
            "desc" -> todoCardRepository.findAllByBoardIdOrderByDateDesc(PageRequest.of(0, 5), id)
            else -> TODO("해당되는 sort가 존재하지 않는 메세지 던지기 NotFoundTag")
        }
    }
}