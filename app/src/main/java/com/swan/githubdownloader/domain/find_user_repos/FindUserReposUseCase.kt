package com.swan.githubdownloader.domain.find_user_repos

import com.swan.githubdownloader.data.api.model.ApiResult
import com.swan.githubdownloader.data.api.model.GithubRepo
import com.swan.githubdownloader.data.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class FindUserReposUseCase @Inject constructor(
    private val repository: GithubRepository
) {

    suspend operator fun invoke(user: String): ApiResult<List<GithubRepo>> {
        return repository.getUserRepos(user)
    }

}