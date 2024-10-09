package com.swan.githubdownloader.data.repository

import com.swan.githubdownloader.data.api.model.ApiResult

interface FileRepository {

    fun downloadFile(url: String, fileName: String, mimeType: String): ApiResult<Unit>

}