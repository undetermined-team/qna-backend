package com.project.meshq.application.member.domain

import com.project.meshq.core.jpa.BaseEntityModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
class Member(
    val email: String,
    val pwd: String,
): BaseEntityModel() {
}