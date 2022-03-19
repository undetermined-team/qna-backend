package com.project.meshq.application.member.adapter.out

import com.project.meshq.application.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
}