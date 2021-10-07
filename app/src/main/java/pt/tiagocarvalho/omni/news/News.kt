package pt.tiagocarvalho.omni.news

sealed class News(
    open val id: String,
    open val title: String
) {
    data class Article(
        override val id: String,
        val imageUrl: String?,
        override val title: String
    ) : News(id, title)

    data class Topic(
        override val id: String,
        override val title: String
    ) : News(id, title)
}
