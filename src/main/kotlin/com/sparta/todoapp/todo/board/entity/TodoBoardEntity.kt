package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.auth.member.entity.Member
import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.card.entity.TodoCard
import jakarta.persistence.*

@Entity
class TodoBoardEntity(
    @Column(name = "board_name")
    private var boardName: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @OneToMany(mappedBy = "board", cascade = [CascadeType.REMOVE])
    val todoCard: List<TodoCard> = mutableListOf();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id", insertable = false, updatable = false)
    val owner: Member? = null;

    fun updateValue(updateData: Map<String, Any>) {
        updateData.keys.forEach {
            when (it) {
                "ownerName" -> boardName = updateData[it] as String;
                else -> throw TODO("BadUpdateRequestException 정의 후 it의 정보 전달")
            }
        }
    }

    fun convertToResponseDto(): ResponseTodoBoardDto {
        val dtoList = mutableListOf<ResponseTodoCardDto>();

        return ResponseTodoBoardDto(id = this.id!!, ownerName = this.boardName)
    }

    companion object {
        fun convertToEntity(dto: RequestTodoBoardDto): TodoBoardEntity = TodoBoardEntity(boardName = dto.boardName!!);
    }
}