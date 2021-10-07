package pt.tiagocarvalho.omni.news

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("articles") val articles: List<ArticleResponse>,
    @SerializedName("topics") val topics: List<TopicResponse>
)
