package com.example.bbcnews.domain.usecase

import com.example.bbcnews.domain.model.Articles
import java.time.Instant
import java.time.format.DateTimeFormatter

class GetDateSortedNewsUseCase {
    suspend operator fun invoke(items: List<Articles>): List<Articles> {
        return items.sortedBy {
            Instant.from(
                DateTimeFormatter.ISO_DATE_TIME.parse(it.publishedAt)).toEpochMilli()
        }
    }
}