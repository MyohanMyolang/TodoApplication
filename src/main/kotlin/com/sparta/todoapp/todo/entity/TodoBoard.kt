package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.todo.dto.ResponseTodoBoardDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TodoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "owner_name")
    private var ownerName: String;

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
        }
        catch (e:Exception){
            TODO("Throw Type Cast Error");
        }
    }

    fun convertToResponseDto() = ResponseTodoBoardDto(id = this.id!!, ownerName = this.ownerName)
}