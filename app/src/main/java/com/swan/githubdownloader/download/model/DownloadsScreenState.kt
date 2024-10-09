package com.swan.githubdownloader.download.model

import com.swan.githubdownloader.data.database.model.RepositoryEntity

data class DownloadsScreenState(
    val downloadedRepos: List<RepositoryEntity> = listOf()
)