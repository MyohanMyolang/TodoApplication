package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class TodoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "owner_name")
    private var ownerName: String;

    @OneToMany(mappedBy = "owner")
    private val todoCard: List<TodoCard> = mutableListOf();

    constructor(ownerName: String) {
        this.ownerName = ownerName;
    }

    /**
     * @param updateValueMap key는 Field명과 일치해야 한다.
     */
    fun updateValue(updateValueMap: Map<String, Any>) {
        try {
            updateValueMap.keys.forEach {
                when (it) {
                    "ownerName" -> ownerName = updateValueMap[it] as String;
                    else -> throw Exception("")
                }
            }
        } catch (e: Exception) {
            TODO("Throw Type Cast Error");
        }
    }

    fun convertToResponseDto(): ResponseTodoBoardDto {
        val dtoList = mutableListOf<ResponseTodoCardDto>();

        todoCard.forEach{
            dtoList.add(it.convertDto());
        }

        return ResponseTodoBoardDto(id = this.id!!, ownerName = this.ownerName, todoCardList = dtoList)
    }
}