package com.swan.githubdownloader.data.api.model

data class DownloadStatus(
    val userName: String,
    val repositoryName: String,
    val status: Int,
    val url: String
)
