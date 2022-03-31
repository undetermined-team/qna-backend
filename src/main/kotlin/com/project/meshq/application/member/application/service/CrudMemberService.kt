package com.project.meshq.application.member.application.service

import com.project.meshq.application.member.adapter.`in`.CrudMemberController
import com.project.meshq.application.member.adapter.out.MemberRepository
import com.project.meshq.application.member.application.port.`in`.CrudMemberUseCase
import com.project.meshq.application.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CrudMemberService(
    val memberRepository: MemberRepository
): CrudMemberUseCase {

    @Transactional
    override fun signUp(member: Member): Long? {
        return memberRepository.save(member).id
    }
}