package usecase

import model.Article
import java.time.Instant
import java.time.format.DateTimeFormatter

class GetDateSortedUseCase {
    operator fun invoke(items: List<Article>): List<Article> {
        return items.sortedByDescending {
            Instant.from(
                DateTimeFormatter.ISO_DATE_TIME.parse(it.publishedAt)).toEpochMilli()
        }
    }
}