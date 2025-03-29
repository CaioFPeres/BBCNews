package com.example.bbcnews.domain.model

data class News (
    var status: String,
    var totalResults: Int,

    var articles: List<Articles>
)

data class Articles (
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