package com.sparta.todoapp.infra.postgresql

import com.sparta.todoapp.domain.todo.board.repository.ITodoBoardRepository
import org.springframework.stereotype.Repository

@Repository
class TodoBoardRepository: ITodoBoardRepository {
}