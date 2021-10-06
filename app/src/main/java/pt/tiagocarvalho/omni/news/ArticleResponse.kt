package pt.tiagocarvalho.omni.news

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("article_id") val articleId: String,
    @SerializedName("title") val title: Title,
    @SerializedName("authors") val authors: List<Authors>,
    @SerializedName("main_resource") val mainResource: MainResource?
) {

    data class Title(
        @SerializedName("value") val value: String
    )

    data class Authors(
        @SerializedName("id") val value: String,
        @SerializedName("title") val title: String
    )

    data class MainResource(
        @SerializedName("image_asset") val imageAsset: ImageAsset?
    ) {

        data class ImageAsset(
            @SerializedName("id") val id: String
        )
    }
}
