package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.todo.dto.RequestTodoCardDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class TodoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "owner")
    private val ownerId: Long;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id", insertable = false, updatable = false)
    val owner: TodoBoard? = null;

    @Column(name = "title")
    private var title: String;

    @Column(name = "isCompleted")
    private var isCompleted: Boolean;

    @Column(name = "date")
    private val date: LocalDateTime = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "detail")
    private var todoCardDetail: TodoCardDetail;


    constructor(ownerId: Long, title: String, isCompleted: Boolean = false, todoCardDetail: TodoCardDetail) {
        this.ownerId = ownerId;
        this.title = title
        this.isCompleted = isCompleted;
        this.todoCardDetail = todoCardDetail;
    }

    fun updateValue(updateData: Map<String, Any>): TodoCard {
        try {
            updateData.keys.forEach {
                when (it) {
                    "title" -> title = updateData[it] as String
                    else -> todoCardDetail.updateValue(it, updateData[it])
                }
            }
            return this
        } catch (e: ClassCastException) {
            throw TODO("BadUpdateRequestException - 타입이 제대로 들어오지 않았습니다.")
        }
    }

    fun convertDto() = ResponseTodoCardDto(id!!, title, isCompleted, date)
    fun convertDetailDto() = ResponseTodoCardDetailDto(
        id = id!!,
        title = title,
        isCompleted = isCompleted,
        description = todoCardDetail.description,
        writer = todoCardDetail.writer
    )

    companion object {
        fun convertToEntity(dto: RequestTodoCardDto): TodoCard {
            val todoCardDetail = TodoCardDetail(writer = dto.writer!!, descript = dto.descript!!)
            return TodoCard(ownerId = dto.targetBoardId!!, title = dto.title!!, todoCardDetail = todoCardDetail)
        }
    }
}