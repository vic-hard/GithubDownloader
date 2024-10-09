package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.GithubApiService
import com.swan.githubdownloader.data.api.model.ApiResult
import com.swan.githubdownloader.data.api.model.GithubRepo
import com.swan.githubdownloader.data.database.dao.RepositoryDao
import com.swan.githubdownloader.data.database.model.RepositoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val apiService: GithubApiService,
    private val repositoryDao: RepositoryDao
) : GithubRepository {

    override suspend fun getUserRepos(user: String): ApiResult<List<GithubRepo>> {

        return try {
            withContext(Dispatchers.IO) {
                val list = apiService.getUserRepos(user)
                if (list.isEmpty()) {
                    ApiResult.Failure(
                        listOf(),
                        "Failed to get repositories or user is not found"
                    )
                } else {
                    ApiResult.Success(list)
                }
            }
        } catch (e: Exception) {
            Timber.e("Error while fetching repo: %s", e.message)
            ApiResult.Error(e)
        }

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