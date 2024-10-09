package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.GithubApiService
import com.swan.githubdownloader.data.api.model.GithubRepo
import com.swan.githubdownloader.data.database.dao.RepositoryDao
import com.swan.githubdownloader.data.database.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: GithubApiService,
    private val repositoryDao: RepositoryDao
) : GithubRepository {

    override suspend fun getUserRepos(user: String): List<GithubRepo> {
        return apiService.getUserRepos(user)
    }

    override suspend fun saveDownloadedRepository(userName: String, repositoryName: String) {
        repositoryDao.insertRepository(
            RepositoryEntity(userName = userName, repoName = repositoryName)
        )
        Timber.d("Repository %s saved to database", repositoryName)
    }

    override fun getDownloadedRepositories(): Flow<List<RepositoryEntity>> {
        return repositoryDao.getAllRepositories()
    }
}