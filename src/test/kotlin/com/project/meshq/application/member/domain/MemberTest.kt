package com.project.meshq.application.member.domain

import com.project.meshq.application.member.adapter.out.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class MemberTest @Autowired constructor(
    val memberRepository: MemberRepository
) {

    @Test
    fun `mappedSuperclass 테스트`() {
        //given
        val member = Member(email = "test@test.com", pwd = "123456789")
        //when
        memberRepository.save(member)
        //then
        assertThat(member.email).isEqualTo("test@test.com")
        assertThat(member.pwd).isEqualTo("123456789")

    }
}