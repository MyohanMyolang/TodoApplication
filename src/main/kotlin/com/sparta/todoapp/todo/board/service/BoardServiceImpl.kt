package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.facade.TodoBoardEntityRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

// TODO: 갈아엎고 다시하기.

@Service
class BoardServiceImpl @Autowired constructor(
    private val todoBoardEntityRepository: TodoBoardEntityRepository,
    private val todoBoardRepository: ITodoBoardRepository
) : BoardService {

    @Transactional
    override fun addTodoBoard(requestTodoBoardDto: RequestTodoBoardDto): ResponseTodoBoardDto =
        todoBoardRepository.addBoard(TodoBoard.from(requestTodoBoardDto)).toResponseDto()

    /**
     * size는 요청할 때 고정으로 보내야 중복 없이 전달 된다.
     * @param page 해당 페이지
     * @param size 가져올 BoardList의 숫자
     * @throws NotFoundTargetException 페이지 범위륿 벗어났을 경우
     */
    @Transactional
    override fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto {
        val foundPage = todoBoardRepository.paginationFindAll(page, size).apply {
            if (this.totalPages < page && page > 1) throw NotFoundTargetException("페이지가 존재하지 않습니다.")
        }
        val responseDtoList = mutableListOf<ResponseTodoBoardDto>();
        foundPage.forEach { responseDtoList.add(it.toResponseDto()) }
        return ResponseTodoBoardWithPageDto(foundPage.totalPages, responseDtoList)
    }
}