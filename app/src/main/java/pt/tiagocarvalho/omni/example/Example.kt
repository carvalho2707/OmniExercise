package pt.tiagocarvalho.omni.example

import com.squareup.moshi.Json

data class Example(
  @Json(name = "image") val image: String?,
  @Json(name = "content") val content: String?,
  @Json(name = "url") val url: String?,
)


enum class Status {
  ok, error
}