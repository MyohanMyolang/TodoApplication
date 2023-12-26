package com.sparta.todoapp.todo.card.entity

import com.sparta.todoapp.auth.member.entity.Member
import com.sparta.todoapp.todo.card.domain.TodoCard
import com.sparta.todoapp.todo.card.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.card.dto.ResponseTodoCardDto
import com.sparta.todoapp.todo.entity.TodoBoardEntity
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "todo_card")
class TodoCardEntity(
    @Column(name = "board")
    val boardId: Long,

    @Column(name = "title")
    var title: String,

    @Column(name = "isCompleted")
    var isCompleted: Boolean = false,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "detail")
    val todoCardDetailEntity: TodoCardDetailEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board", referencedColumnName = "id", insertable = false, updatable = false)
    val board: TodoBoardEntity? = null;

    @Column(name = "date")
    private val date: LocalDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id", insertable = false, updatable = false)
    val owner: Member? = null;


    fun updateValue(updateData: Map<String, Any>): TodoCardEntity {
        try {
            updateData.keys.forEach {
                when (it) {
                    "title" -> title = updateData[it] as String
                    else -> todoCardDetailEntity.updateValue(it, updateData[it])
                }
            }
            return this
        } catch (e: ClassCastException) {
            throw TODO("BadUpdateRequestException - 타입이 제대로 들어오지 않았습니다.")
        }
    }

    fun toResponseDto() = ResponseTodoCardDto(
        id = id!!,
        title = title,
        isCompleted = isCompleted,
        date = date
    )

    fun toDetailResponseDto() = ResponseTodoCardDetailDto(
        id = id!!,
        title = title,
        isCompleted = isCompleted,
        date = date,
        writer = todoCardDetailEntity.writer,
        description = todoCardDetailEntity.description
    )
}