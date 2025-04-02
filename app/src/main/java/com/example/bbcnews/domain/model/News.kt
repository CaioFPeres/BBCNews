package model

data class News (
    var status: String,
    var totalResults: Int,

    var articles: List<Article>
)

data class Article (
    var source: Source,

    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,
)

data class Source (
    var id: String,
    var name: String,
)