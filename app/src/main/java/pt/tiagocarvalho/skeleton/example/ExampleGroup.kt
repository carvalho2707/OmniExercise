package pt.tiagocarvalho.skeleton.example

import com.google.gson.annotations.SerializedName

data class ExampleGroup(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ExampleResponse>,
)

data class ExampleResponse(
    @SerializedName("author") val author: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("content") val content: String?,
)
