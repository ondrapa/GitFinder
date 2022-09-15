package com.example.gitusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {

    @GET("users/{user}/repos")
    suspend fun getRepo(@Path("user") user: String): List<RepoDto>

}