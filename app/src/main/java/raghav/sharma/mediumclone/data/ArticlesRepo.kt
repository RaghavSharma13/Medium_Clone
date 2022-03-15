package raghav.sharma.mediumclone.data

import raghav.sharma.api.ConduitClient

object ArticlesRepo {
    private val api = ConduitClient.publicApi
    private val authApi = ConduitClient.authApi

    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getMyFeed() = authApi.getFeedArticles()
    suspend fun getArticle(slug: String) = api.getArticleBySlug(slug)
}