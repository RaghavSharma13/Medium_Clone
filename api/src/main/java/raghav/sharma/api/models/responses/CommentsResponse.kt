package raghav.sharma.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import raghav.sharma.api.models.entities.Comment

@JsonClass(generateAdapter = true)
data class CommentsResponse(
    @Json(name = "comments")
    val comments: List<Comment>
)