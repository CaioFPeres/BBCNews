package usecase

import model.Article
import java.time.Instant
import java.time.format.DateTimeFormatter

class GetDateSortedNewsUseCase {
    operator fun invoke(items: List<Article>): List<Article> {
        return items.sortedBy {
            Instant.from(
                DateTimeFormatter.ISO_DATE_TIME.parse(it.publishedAt)).toEpochMilli()
        }
    }
}