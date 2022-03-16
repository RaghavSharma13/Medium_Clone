package raghav.sharma.mediumclone.data

import raghav.sharma.api.ConduitClient
import raghav.sharma.api.models.entities.PostArticleCreds
import raghav.sharma.api.models.requests.CreateArticleRequest

object ArticlesRepo {
    private val api = ConduitClient.publicApi
    private val authApi = ConduitClient.authApi

    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getMyFeed() = authApi.getFeedArticles()
    suspend fun getArticle(slug: String) = api.getArticleBySlug(slug)
    suspend fun publishArticle(
        title: String,
        body: String,
        tagList: List<String>?,
        description: String
    ) =
        authApi.publishArticle(
            CreateArticleRequest(
                PostArticleCreds(
                    title = title,
                    body = body,
                    tagList = tagList,
                    description = description
                )
            )
        )
}