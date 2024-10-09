package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.model.GithubRepo

interface GithubRepository {
    suspend fun getUserRepos(user: String): List<GithubRepo>
}