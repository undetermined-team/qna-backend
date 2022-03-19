package com.project.meshq.core.jpa

import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntityModel {
    @Id
    @GeneratedValue
    val id: Long = 0
    val createdAt: LocalDateTime? = null
    val updatedAt: LocalDateTime? = null
}