package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.model.DownloadStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DownloadStatusRepositoryImpl @Inject constructor() : DownloadStatusRepository {

    private val _downloadsFlow = MutableSharedFlow<Map<Long, DownloadStatus>>(Int.MAX_VALUE)
    override val downloadsFlow: SharedFlow<Map<Long, DownloadStatus>> = _downloadsFlow.asSharedFlow()

    private val downloads = mutableMapOf<Long, DownloadStatus>()

    private val repositoryScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun addDownload(
        downloadId: Long,
        userName: String,
        repositoryName: String,
        status: Int,
        url: String
    ) {
        Timber.d("Downloading repository: %s with id: %s", repositoryName, downloadId)
        repositoryScope.launch {
            downloads[downloadId] = DownloadStatus(userName, repositoryName, status, url)
            emitDownloads()
        }
    }

    override fun updateDownloadStatus(downloadId: Long, status: Int) {
        repositoryScope.launch {
            downloads[downloadId]?.let {
                downloads[downloadId] = it.copy(status = status)
                emitDownloads()
            }
        }
    }

    override fun removeDownload(downloadId: Long) {
        Timber.d("Stopped downloading repository with id %s", downloadId)
        repositoryScope.launch {
            downloads.remove(downloadId)
            emitDownloads()
        }
    }

    private suspend fun emitDownloads() {
        _downloadsFlow.emit(downloads.toMap())
    }

}