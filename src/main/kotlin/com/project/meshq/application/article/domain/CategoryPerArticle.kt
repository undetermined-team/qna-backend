package com.project.meshq.application.article.domain

import com.project.meshq.core.jpa.BaseEntityModel
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class CategoryPerArticle(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    val article: Article,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_category_id")
    val articleCategory: ArticleCategory
): BaseEntityModel() {
}