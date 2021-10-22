package com.wahyuapp.ghapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.github.com/")
    .build()

//API interface
interface GithubService {
    @GET("repos/laravel/laravel/contributors")
    suspend fun showList():
            List<GithubData>

    @GET("users/{user}")
    suspend fun showUser(@Path("user") user: String):
            GithubData
}

object GithubApi {
    val retrofitService: GithubService = retrofit.create(GithubService::class.java)
}