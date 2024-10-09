package com.swan.githubdownloader.data.api

import com.swan.githubdownloader.data.api.model.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): List<GithubRepo>

}