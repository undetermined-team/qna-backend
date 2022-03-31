package com.project.meshq.application.article.domain

import com.project.meshq.core.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Article(
    val title: String,
    val body: String,

    @OneToMany(mappedBy = "article")
    val categoryPerArticle: MutableSet<CategoryPerArticle>

): BaseEntity() {
}