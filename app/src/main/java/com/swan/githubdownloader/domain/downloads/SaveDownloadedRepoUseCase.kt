package com.swan.githubdownloader.domain.downloads

import com.swan.githubdownloader.data.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveDownloadedRepoUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(userName: String, repoName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveDownloadedRepository(userName, repoName)
        }
    }
}