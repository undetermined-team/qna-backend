package com.project.meshq.application.member.domain

import com.project.meshq.application.member.adapter.out.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Transactional
@SpringBootTest
internal class MemberTest @Autowired constructor(
    val memberRepository: MemberRepository,
    val entityManager: EntityManager
) {

    @Test
    fun `audit 테스트`() {
        //given
        val member = Member(email = "test@test.com", pwd = "123456789", name = "test_name", role = Role.USER)
        //when
        val memberEntity = memberRepository.save(member)
        entityManager.flush()
        entityManager.clear()
        //then
        assertThat(memberEntity.createdAt).isNotNull
        assertThat(memberEntity.updatedAt).isNotNull
    }

    @Test
    fun `equal 테스트`() {
        //given
        val member = Member(email = "test@test.com", pwd = "123456789", name = "test_name", role = Role.USER)
        //when
        val memberEntity1 = memberRepository.save(member)
        entityManager.flush()
        entityManager.clear()
        //then
        val memberEntity2 = memberRepository.findById(memberEntity1.id!!).get()
        assertThat(memberEntity1).isEqualTo(memberEntity2)
    }
}