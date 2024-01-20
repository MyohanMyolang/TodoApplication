package com.sparta.todoapp.common.member.repository

import com.sparta.todoapp.common.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberEntityRepository : JpaRepository<MemberEntity, String> {
}
