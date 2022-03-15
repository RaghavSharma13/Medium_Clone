package raghav.sharma.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import raghav.sharma.api.models.entities.Error

@JsonClass(generateAdapter = true)
data class ErrorsResponse(
    @Json(name = "errors")
    val errors: Error
)