package com.project.meshq.application.member.application.port.`in`

import com.project.meshq.application.member.adapter.`in`.CrudMemberController
import com.project.meshq.application.member.adapter.`in`.CrudMemberController.MemberSignUp
import com.project.meshq.application.member.domain.Member

interface CrudMemberUseCase {
    fun signUp(member: Member): Long?
}