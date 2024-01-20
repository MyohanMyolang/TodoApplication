package com.sparta.todoapp.common.member.service

import com.sparta.todoapp.common.member.auth.dto.SignDto
import com.sparta.todoapp.common.member.entity.MemberEntity
import com.sparta.todoapp.common.member.repository.IMemberRepository
import com.sparta.todoapp.common.member.type.UserRole
import com.sparta.todoapp.system.error.exception.AlreadyHasMember
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
	private val memberRepository: IMemberRepository
) {

	fun <T> checkHasMember(id: String, func: () -> T): T =
		memberRepository.findByIdOrNull(id)
			?.let { throw AlreadyHasMember("id - ${id}는 사용할 수 없습니다.") }
			?: func.invoke()


	@Transactional
	fun addMember(dto: SignDto, role: UserRole): Unit {
		checkHasMember(dto.id!!) {
			MemberEntity.of(dto, role)
				.let { memberRepository.save(it) }
		}
	}

	fun getMember(id: String) =
		memberRepository.findById(id)
}