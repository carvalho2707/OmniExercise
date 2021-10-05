package pt.tiagocarvalho.omni.example

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExampleGroup(
  @Json(name = "status") val status: String,
  @Json(name = "totalResults") val totalResults: Int,
  @Json(name = "articles") val articles: List<ExampleResponse>,
)

@JsonClass(generateAdapter = true)
data class ExampleResponse(
  @Json(name = "author") val author: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "url") val url: String?,
  @Json(name = "urlToImage") val urlToImage: String?,
  @Json(name = "publishedAt") val publishedAt: String?,
  @Json(name = "content") val content: String?,
)