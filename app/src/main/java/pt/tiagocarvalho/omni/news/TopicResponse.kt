package pt.tiagocarvalho.omni.news

import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("topic_id") val topicId: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: Image?
) {

    data class Image(
        @SerializedName("image_asset") val imageAsset: ImageAsset?
    ) {

        data class ImageAsset(
            @SerializedName("id") val id: String
        )
    }
}
