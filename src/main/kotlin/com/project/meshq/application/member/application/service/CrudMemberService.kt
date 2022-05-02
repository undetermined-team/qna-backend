package com.project.meshq.application.member.application.service

import com.project.meshq.application.member.adapter.`in`.MemberLogin
import com.project.meshq.application.member.adapter.`in`.MemberSignUp
import com.project.meshq.application.member.adapter.`in`.ResponseMember
import com.project.meshq.application.member.adapter.out.MemberRepository
import com.project.meshq.application.member.application.port.`in`.CrudMemberUseCase
import com.project.meshq.application.member.domain.Role
import com.project.meshq.application.util.ExceptionType
import com.project.meshq.application.util.throwCheck
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CrudMemberService(
    val memberRepository: MemberRepository,
    val passwordEncoder: PasswordEncoder
): CrudMemberUseCase {

    @Transactional
    override fun signUp(memberSignUp: MemberSignUp): Long? {
        val member = memberSignUp.toDomain()
        member.pwd = passwordEncoder.encode(member.pwd)
        member.role = Role.USER

        return memberRepository.save(member).id
    }

    @Transactional(readOnly = true)
    override fun login(memberLogin: MemberLogin): ResponseMember {
        val member = memberRepository.findByEmail(memberLogin.email)
        throwCheck(member, ExceptionType.TEST)

        if(!passwordEncoder.matches(memberLogin.pwd, member.pwd))
            throw RuntimeException("TEST")

        return ResponseMember(member.email, member.role, member.snsId)
    }


}