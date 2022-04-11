package com.project.meshq.application.member.application.service

import com.project.meshq.application.member.adapter.out.MemberRepository
import com.project.meshq.application.util.ExceptionType
import com.project.meshq.application.util.throwCheck
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomUserDetailService(
    private val memberRepository: MemberRepository
): UserDetailsService{

    override fun loadUserByUsername(email: String): UserDetails {
        val member = memberRepository.findByEmail(email)
        throwCheck(member, ExceptionType.TEST)

        val authoriteies =  listOf(SimpleGrantedAuthority(member.role.code))
        return User(member.email, "", authoriteies)
    }
}