package raghav.sharma.api.services

import raghav.sharma.api.models.requests.CreateArticleRequest
import raghav.sharma.api.models.requests.UserUpdateRequest
import raghav.sharma.api.models.responses.ArticleResponse
import raghav.sharma.api.models.responses.ArticlesResponse
import raghav.sharma.api.models.responses.ProfileResponse
import raghav.sharma.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAuthApi {

    @GET("user")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body userUpdateRequest: UserUpdateRequest
    ): Response<UserResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @DELETE("profiles/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(@Path("slug") slug: String): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @POST("articles")
    suspend fun publishArticle(
        @Body createArticleRequest: CreateArticleRequest
    ): Response<ArticleResponse>
}