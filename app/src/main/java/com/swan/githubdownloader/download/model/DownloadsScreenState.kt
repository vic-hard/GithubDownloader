package com.swan.githubdownloader.download.model

import com.swan.githubdownloader.data.api.model.DownloadStatus
import com.swan.githubdownloader.data.database.model.RepositoryEntity

data class DownloadsScreenState(
    val downloadedRepos: List<RepositoryEntity> = listOf(),
    val downloadingRepos: List<DownloadStatus> = listOf()
)