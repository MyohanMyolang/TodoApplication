package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.entity.TodoBoard
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.repository.TodoBoardRepository
import com.sparta.todoapp.todo.card.repository.TodoCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl @Autowired constructor(
    private val todoBoardRepository: TodoBoardRepository,
    private val todoCardRepository: TodoCardRepository
) : BoardService {

    override fun addTodoBoard(todoBoard: TodoBoard): ResponseTodoBoardDto {
        // 권한 확인
        return todoBoardRepository.save(todoBoard).convertToResponseDto();
    }
    
    // TODO: Board 삭제 할 때도 권한 확인

    /**
     * size는 요청할 때 고정으로 보내야 중복 없이 전달 된다.
     * @param page 해당 페이지
     * @param size 가져올 BoardList의 숫자
     * @throws NotFoundTargetException 페이지 범위륿 벗어났을 경우
     */
    override fun getTodoBoardList(page: Int, size: Int, sort: String): ResponseTodoBoardWithPageDto {
        val findAll = todoBoardRepository.findAll(PageRequest.of(page - 1, size))
        if(findAll.totalPages < page && page > 1) throw NotFoundTargetException("페이지가 존재하지 않습니다.")
        val result = mutableListOf<ResponseTodoBoardDto>();
        findAll.forEach {
            val todoCard =when(sort) {
                "asc" -> todoCardRepository.findAllByBoardIdOrderByDateAsc(PageRequest.of(0, 5), it.getId()!!)
                "desc" -> todoCardRepository.findAllByBoardIdOrderByDateDesc(PageRequest.of(0, 5), it.getId()!!)
                else -> TODO("해당되는 sort가 존재하지 않는 메세지 던지기 NotFoundTag")
            }
            result.add(it.convertToResponseDto((todoCard)))
        }
        return ResponseTodoBoardWithPageDto(page = findAll.totalPages, result);
    }
}