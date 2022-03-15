package raghav.sharma.api.services

import raghav.sharma.api.models.entities.User
import raghav.sharma.api.models.entities.UserCreds
import raghav.sharma.api.models.requests.LoginRequest
import raghav.sharma.api.models.requests.SignUpRequest
import raghav.sharma.api.models.responses.ArticleResponse
import raghav.sharma.api.models.responses.ArticlesResponse
import raghav.sharma.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAPI {

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null,
        @Query("tag") tag: String? = null
    ): Response<ArticlesResponse>

    @POST("users")
    suspend fun signUpUser(
        @Body req: SignUpRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body req: LoginRequest
    ): Response<UserResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
        @Path("slug") slug: String
    ): Response<ArticleResponse>
}