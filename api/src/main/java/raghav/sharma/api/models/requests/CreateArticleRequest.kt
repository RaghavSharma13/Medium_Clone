package raghav.sharma.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import raghav.sharma.api.models.entities.PostArticleCreds


@JsonClass(generateAdapter = true)
data class CreateArticleRequest(
    @Json(name = "article")
    val article: PostArticleCreds
)