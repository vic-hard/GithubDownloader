package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.model.DownloadStatus
import kotlinx.coroutines.flow.SharedFlow

interface DownloadStatusRepository {
    val downloadsFlow: SharedFlow<Map<Long, DownloadStatus>>
    fun addDownload(
        downloadId: Long,
        userName: String,
        repositoryName: String,
        status: Int,
        url: String
    )
    fun removeDownload(downloadId: Long)
    fun updateDownloadStatus(downloadId: Long, status: Int)
}