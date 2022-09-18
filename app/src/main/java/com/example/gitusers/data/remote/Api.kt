package com.example.gitusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("users/{user}/repos")
    suspend fun getRepo(
        @Path("user") user: String
    ): List<RepoDto>

    @GET("repos/{user}/{repo}/branches")
    suspend fun getBranches(
        @Path("user") owner: String,
        @Path("repo") repoName: String
    ): List<BranchDto>

    @GET("repos/{user}/{repo}/commits")
    suspend fun getLatestCommits(
        @Path("user") owner: String,
        @Path("repo") repoName: String
    ): List<CommitDto>

}