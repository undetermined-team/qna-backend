package com.project.meshq.application.member.domain

import com.project.meshq.core.jpa.BaseEntity
import javax.persistence.Entity

@Entity
class Member(
    val email: String,
    val name: String,
    val pwd: String,
): BaseEntity() {

}