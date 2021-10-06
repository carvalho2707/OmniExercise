package pt.tiagocarvalho.omni.example

data class Example(
    val image: String?,
    val content: String?,
    val url: String?,
)

enum class Status {
    ok, error
}
