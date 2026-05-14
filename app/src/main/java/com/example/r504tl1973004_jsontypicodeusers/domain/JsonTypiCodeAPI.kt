package com.example.r504tl1973004_jsontypicodeusers.domain

import com.example.r504tl1973004_jsontypicodeusers.presentation.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private val api =
    Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(
        GsonConverterFactory.create()
    ).build()


interface JsonTypiCodeAPI {
    // GET:: https://jsonplaceholder.typicode.com/users
    @GET("users")
    suspend fun getAllUsers() : List<User>

    suspend fun getUserById(@Path("id") id: Int)

    @POST("users")
    suspend fun createUser(@Body reqData : CreateUserReqDto) : User

}

val jsonTypicodeService = api.create<JsonTypiCodeAPI>()
// jsonTypicodeService.getAllUsers()