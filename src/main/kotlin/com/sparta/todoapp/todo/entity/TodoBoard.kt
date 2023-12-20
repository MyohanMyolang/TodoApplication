package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.todo.dto.RequestTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class TodoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    fun getId() = id;

    @Column(name = "owner_name")
    private var ownerName: String;

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
    private val todoCard: List<TodoCard> = mutableListOf();

    constructor(ownerName: String) {
        this.ownerName = ownerName;
    }

    /**
     * @param updateData key는 Field명과 일치해야 한다.
     */
    fun updateValue(updateData: Map<String, Any>) {
        updateData.keys.forEach {
            when (it) {
                "ownerName" -> ownerName = updateData[it] as String;
                else -> throw TODO("BadUpdateRequestException 정의 후 it의 정보 전달")
            }
        }
    }

    fun convertToResponseDto(todoCard: List<TodoCard>): ResponseTodoBoardDto {
        val dtoList = mutableListOf<ResponseTodoCardDto>();

        todoCard.forEach {
            dtoList.add(it.convertDto());
        }

        return ResponseTodoBoardDto(id = this.id!!, ownerName = this.ownerName, todoCardList = dtoList)
    }

    fun convertToResponseDto(): ResponseTodoBoardDto {
        val dtoList = mutableListOf<ResponseTodoCardDto>();

        return ResponseTodoBoardDto(id = this.id!!, ownerName = this.ownerName)
    }

    companion object {
        fun convertToEntity(dto: RequestTodoBoardDto): TodoBoard = TodoBoard(ownerName = dto.ownerName!!);
    }
}