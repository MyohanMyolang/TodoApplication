package com.sparta.todoapp.infra.postgresql

import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.IMemberRepository
import com.sparta.todoapp.common.member.repository.MemberEntityRepository
import com.sparta.todoapp.system.error.exception.NotFoundTargetException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PostgresMemberRepository(
	private val memberEntityRepository: MemberEntityRepository
) : IMemberRepository {
	override fun save(entity: MemberEntity) = memberEntityRepository.save(entity)
	override fun findById(id: String) =
		memberEntityRepository.findByIdOrNull(id) ?: throw NotFoundTargetException("해당 ID는 존재하지 않습니다.")

	override fun findByIdOrNull(id: String): MemberEntity? = memberEntityRepository.findByIdOrNull(id)

}