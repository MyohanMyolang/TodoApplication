package com.sparta.todoapp.todo.board.service

import com.sparta.todoapp.auth.IAuth
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import com.sparta.todoapp.todo.board.domain.TodoBoard
import com.sparta.todoapp.todo.board.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.board.repository.ITodoBoardRepository
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BoardServiceImpl @Autowired constructor(
	private val todoBoardRepository: ITodoBoardRepository,
	private val auth: IAuth,
	private val entityManager: EntityManager
) : BoardService {

	@Transactional
	override fun addTodoBoard(requestTodoBoardDto: RequestTodoBoardDto): ResponseTodoBoardDto {
		val owner = auth.getCurrentMemberEntity()
		val board = TodoBoard.from(requestTodoBoardDto)
		return todoBoardRepository.addBoard(board, owner).toResponseDto()
	}

	override fun getBoardListByName(name: String): List<ResponseTodoBoardDto> {
		return todoBoardRepository.findBoardListByName(name).run {
			val resultList = mutableListOf<ResponseTodoBoardDto>()
			if (this.isEmpty()) throw NotFoundTargetException("${name}의 Board가 존재하지 않습니다.")
			this.forEach {
				resultList.add(it.toResponseDto())
			}
			resultList
		}
	}

	/**
	 * size는 요청할 때 고정으로 보내야 중복 없이 전달 된다.
	 * @param page 해당 페이지
	 * @param size 가져올 BoardList의 숫자
	 * @throws NotFoundTargetException 페이지 범위륿 벗어났을 경우
	 */
	@Transactional
	override fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto {
		val foundPage = todoBoardRepository.paginationFindAll(page, size).apply {
			if (this.totalPages < page) throw NotFoundTargetException("페이지가 존재하지 않습니다.")
		}
		val responseDtoList = mutableListOf<ResponseTodoBoardDto>()
		foundPage.forEach { responseDtoList.add(it.toResponseDto()) }
		return ResponseTodoBoardWithPageDto(foundPage.totalPages, responseDtoList)
	}

	override fun getBoardById(id: Long) =
		todoBoardRepository.findBoardById(id) ?: throw NotFoundTargetException("Board가 존재하지 않습니다.")

	override fun deleteBoardById(id: Long) {
		val board = getBoardById(id)
		todoBoardRepository.deleteBoard(board)
	}
}