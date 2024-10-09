package com.swan.githubdownloader.domain.downloads

import com.swan.githubdownloader.data.api.model.ApiResult
import com.swan.githubdownloader.data.repository.FileRepository
import javax.inject.Inject

class DownloadFileUseCase @Inject constructor(
    private val repository: FileRepository
) {

    operator fun invoke(url: String, fileName: String, mimeType: String): ApiResult<Unit> {
        return repository.downloadFile(url, fileName, mimeType)
    }
}