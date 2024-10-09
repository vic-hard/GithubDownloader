package com.swan.githubdownloader.domain.downloads

import com.swan.githubdownloader.data.database.model.RepositoryEntity
import com.swan.githubdownloader.data.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDownloadedReposUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(): Flow<List<RepositoryEntity>> {
        return repository.getDownloadedRepositories().flowOn(Dispatchers.IO)
    }
}