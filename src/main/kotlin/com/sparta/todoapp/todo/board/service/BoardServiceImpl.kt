package com.sparta.todoapp.todo.service

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity
import com.sparta.todoapp.todo.exception.NotFoundTargetException
import com.sparta.todoapp.todo.repository.TodoBoardEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

// TODO: 갈아엎고 다시하기.

@Service
class BoardServiceImpl @Autowired constructor(
    private val todoBoardEntityRepository: TodoBoardEntityRepository,
) : BoardService {

    override fun addTodoBoard(todoBoard: TodoBoardEntity): ResponseTodoBoardDto {
        // 권한 확인
        return todoBoardEntityRepository.save(todoBoard).convertToResponseDto();
    }
    
    // TODO: Board 삭제 할 때도 권한 확인

    /**
     * size는 요청할 때 고정으로 보내야 중복 없이 전달 된다.
     * @param page 해당 페이지
     * @param size 가져올 BoardList의 숫자
     * @throws NotFoundTargetException 페이지 범위륿 벗어났을 경우
     */
    override fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto {
        val findAll = todoBoardEntityRepository.findAll(PageRequest.of(page - 1, size))
        if(findAll.totalPages < page && page > 1) throw NotFoundTargetException("페이지가 존재하지 않습니다.")
        val result = mutableListOf<ResponseTodoBoardDto>();
        return ResponseTodoBoardWithPageDto(page = findAll.totalPages, result);
    }
}