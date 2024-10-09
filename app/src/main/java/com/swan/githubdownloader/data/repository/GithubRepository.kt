package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.model.GithubRepo
import com.swan.githubdownloader.data.database.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getUserRepos(user: String): List<GithubRepo>
    suspend fun saveDownloadedRepository(userName: String, repositoryName: String)
    fun getDownloadedRepositories(): Flow<List<RepositoryEntity>>
}