package com.sparta.todoapp.common.member.repository

import com.sparta.todoapp.common.member.entity.MemberEntity

interface IMemberRepository {
	fun save(entity: MemberEntity): MemberEntity
	fun findById(id: String): MemberEntity
}