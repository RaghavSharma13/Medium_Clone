package raghav.sharma.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import raghav.sharma.api.models.entities.UserCreds
import raghav.sharma.api.models.requests.SignUpRequest
import kotlin.random.Random

class ConduitClientTests {

    private val conduitClient = ConduitClient

    @Test
    fun `GET articles`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(author = "Gerome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tag`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(tag = "welcome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `register new User`() {
        val userCreds = UserCreds(
            email = "test${Random.nextInt(999, 9999)}@test.com",
            password = "pass${Random.nextInt(9999, 99999)}",
            username = "rand_user${Random.nextInt(99, 999)}"
        )

        runBlocking {
            val res = conduitClient.publicApi.signUpUser(SignUpRequest(userCreds))
            assertEquals(userCreds.username, res.body()?.user?.username)
        }
    }

    @Test
    fun `get my feed`(){
        runBlocking {
            val res = conduitClient.authApi.getFeedArticles()
            assertNotNull(res.body()?.articles)
        }
    }
}