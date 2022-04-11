package com.project.meshq.application.member.adapter.out

import com.project.meshq.application.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member
}