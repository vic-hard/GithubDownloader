package com.swan.githubdownloader.domain.downloads

import com.swan.githubdownloader.data.api.model.DownloadStatus
import com.swan.githubdownloader.data.repository.DownloadStatusRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetCurrentDownloadsUseCase @Inject constructor(
    private val repository: DownloadStatusRepository
) {
    operator fun invoke(): SharedFlow<Map<Long, DownloadStatus>> = repository.downloadsFlow
}