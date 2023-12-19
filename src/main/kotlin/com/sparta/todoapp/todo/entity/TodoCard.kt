package com.sparta.todoapp.todo.entity

import com.sparta.todoapp.todo.dto.ResponseTodoCardDetailDto
import com.sparta.todoapp.todo.dto.ResponseTodoCardDto
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime


@Entity
class TodoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null;

    @Column(name = "owner")
    private val ownerId: Long;

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
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
        updateData.keys.forEach {
            when (it) {
                "title" -> title = updateData[it] as String? ?: throw TODO("요청 받은 key에 value가 Null");
                else -> todoCardDetail.updateValue(it, updateData[it] ?: throw TODO("요청 받은 key에 value가 Null"))
            }
        }
        return this
    }

    fun convertDto() = ResponseTodoCardDto(id!!, title, isCompleted, date)
    fun convertDetailDto() = ResponseTodoCardDetailDto(
        id = id!!,
        title = title,
        isCompleted = isCompleted,
        descript = todoCardDetail.descript,
        writer = todoCardDetail.writer
    )
}