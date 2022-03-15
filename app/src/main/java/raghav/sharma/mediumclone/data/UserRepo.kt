package raghav.sharma.mediumclone.data

import raghav.sharma.api.ConduitClient
import raghav.sharma.api.models.entities.User
import raghav.sharma.api.models.entities.UserCreds
import raghav.sharma.api.models.entities.UserLoginCreds
import raghav.sharma.api.models.entities.UserUpdateCreds
import raghav.sharma.api.models.requests.LoginRequest
import raghav.sharma.api.models.requests.SignUpRequest
import raghav.sharma.api.models.requests.UserUpdateRequest
import raghav.sharma.api.models.responses.UserResponse

object UserRepo {
    private val api = ConduitClient.publicApi
    private val authApi = ConduitClient.authApi

    suspend fun getCurrentUser(token: String): UserResponse?{
        ConduitClient.authToken = token
        val res = authApi.getCurrentUser()
        return res.body()
    }

    fun logoutUser(){
        ConduitClient.authToken = null
    }

    suspend fun loginUser(email: String, password: String): UserResponse? {
        val res = api.loginUser(LoginRequest(UserLoginCreds(email = email, password = password)))

        ConduitClient.authToken = res.body()?.user?.token
        return res.body()
    }

    suspend fun getUserProfile() = authApi.getCurrentUser()

    suspend fun signUpUser(email: String, password: String, username: String): UserResponse? {
        val res = api.signUpUser(SignUpRequest(UserCreds(email, password, username)))

        ConduitClient.authToken = res.body()?.user?.token

        return res.body()
    }

    suspend fun updateUser(
        email: String?,
        password: String?,
        username: String?,
        bio: String,
        imageUrl: String
    ): UserResponse? {
        val res = authApi.updateCurrentUser(
            UserUpdateRequest(
                UserUpdateCreds(
                    email = email,
                    password = password,
                    username = username,
                    bio = bio,
                    image = imageUrl
                )
            )
        )

        return res.body()
    }
}