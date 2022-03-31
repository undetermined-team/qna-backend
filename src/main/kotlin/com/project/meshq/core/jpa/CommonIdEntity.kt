package com.project.meshq.core.jpa

import org.hibernate.Hibernate
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class CommonIdEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CommonIdEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}