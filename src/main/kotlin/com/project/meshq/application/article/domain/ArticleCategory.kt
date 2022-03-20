package com.project.meshq.application.article.domain

import com.project.meshq.core.jpa.BaseEntityModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
class ArticleCategory(
    val name: String
): BaseEntityModel() {
}