package com.project.meshq.application.member.domain

import com.project.meshq.core.jpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Member(
    @Column(unique = true)
    val email: String,
    val name: String = "",
    var pwd: String,
    val snsId: String? = null,
    var role: Role = Role.UNKNOWN
): BaseEntity() {

}

