package pt.tiagocarvalho.omni.news

import pt.tiagocarvalho.omni.model.Error
import pt.tiagocarvalho.omni.model.Result
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchService: SearchService
) {

    suspend fun search(term: String, searchFilter: SearchFilter): Result<List<News>, Error> {
        return try {
            val searchResponse = searchService.search(term)
            val news = when (searchFilter) {
                SearchFilter.ARTICLES -> searchResponse.articles.map { it.toDomainModel() }
                SearchFilter.TOPICS -> searchResponse.topics.map { it.toDomainModel() }
            }
            Result.Success(news)
        } catch (e: Exception) {
            Result.Failure(Error(e.message))
        }
    }

    private fun ArticleResponse.toDomainModel(): News {
        return News(
            id = this.articleId,
            imageUrl = createImageUrl(this.mainResource?.imageAsset?.id),
            title = this.title.value
        )
    }

    private fun TopicResponse.toDomainModel(): News {
        return News(
            id = this.topicId,
            imageUrl = null,
            title = this.title
        )
    }

    private fun createImageUrl(imageId: String?): String? {
        return imageId?.let { "$IMAGE_PREFIX$it" }
    }

    companion object {
        private const val IMAGE_PREFIX = "https://gfx-android.omni.se/images/"
    }
}
