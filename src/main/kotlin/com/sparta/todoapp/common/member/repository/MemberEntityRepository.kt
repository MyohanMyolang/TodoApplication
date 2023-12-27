package com.sparta.todoapp.common.member.repository

import com.sparta.todoapp.common.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberEntityRepository : JpaRepository<MemberEntity, Long> {
    fun findByKey(key: String): MemberEntity?
    fun findByMemberId(memberId: String): MemberEntity?
}
