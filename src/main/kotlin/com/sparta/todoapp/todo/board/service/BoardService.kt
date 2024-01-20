
import com.sparta.todoapp.todo.board.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.board.dto.ResponseTodoBoardWithPageDto
import com.sparta.todoapp.todo.board.entity.TodoBoardEntity

interface BoardService {
	fun addTodoBoard(requestTodoBoardDto: RequestTodoBoardDto): ResponseTodoBoardDto
	fun getTodoBoardList(page: Int, size: Int): ResponseTodoBoardWithPageDto
	fun getBoardById(id: Long): TodoBoardEntity
	fun getBoardListByName(name: String): List<ResponseTodoBoardDto>
}