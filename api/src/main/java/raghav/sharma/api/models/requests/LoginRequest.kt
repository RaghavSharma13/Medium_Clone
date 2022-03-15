package raghav.sharma.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import raghav.sharma.api.models.entities.UserLoginCreds

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val user: UserLoginCreds
)