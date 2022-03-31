package com.project.meshq.application.article.domain

import com.project.meshq.core.jpa.BaseEntity
import javax.persistence.Entity

@Entity
class ArticleCategory(
    val name: String
): BaseEntity() {
}