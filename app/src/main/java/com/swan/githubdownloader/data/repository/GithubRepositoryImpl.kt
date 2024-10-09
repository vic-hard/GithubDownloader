package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.GithubApiService
import com.swan.githubdownloader.data.api.model.GithubRepo
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: GithubApiService
) : GithubRepository {

    override suspend fun getUserRepos(user: String): List<GithubRepo> {
        return apiService.getUserRepos(user)
    }

}