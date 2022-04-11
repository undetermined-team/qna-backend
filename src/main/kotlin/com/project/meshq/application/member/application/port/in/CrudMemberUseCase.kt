package com.project.meshq.application.member.application.port.`in`

import com.project.meshq.application.member.adapter.`in`.MemberLogin
import com.project.meshq.application.member.adapter.`in`.MemberSignUp
import com.project.meshq.application.member.adapter.`in`.ResponseMember
import com.project.meshq.application.member.domain.Member

interface CrudMemberUseCase {
    fun signUp(memberSignUp: MemberSignUp): Long?
    fun login(memberLogin: MemberLogin): ResponseMember
}