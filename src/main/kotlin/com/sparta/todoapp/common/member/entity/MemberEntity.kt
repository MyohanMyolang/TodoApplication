package com.sparta.todoapp.common.member.entity

import com.sparta.todoapp.todo.card.entity.TodoCardEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null;

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
    val cardList: List<TodoCardEntity> = mutableListOf();

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.REMOVE])
    val boardList: List<TodoCardEntity> = mutableListOf();

    @Column(name = "key", nullable = false)
    val key: String? = null
}