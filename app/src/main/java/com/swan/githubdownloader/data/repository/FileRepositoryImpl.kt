package com.swan.githubdownloader.data.repository

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import com.swan.githubdownloader.data.api.model.ApiResult
import com.swan.githubdownloader.util.Consts
import com.swan.githubdownloader.util.extractUserNameFromGithubUrl
import timber.log.Timber
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val downloadStatusRepository: DownloadStatusRepository,
    private val downloadManager: DownloadManager
) : FileRepository {

    override fun downloadFile(url: String, fileName: String, mimeType: String): ApiResult<Unit> {
        Timber.d("Downloading file at %s", url)
        return try {
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle(fileName)
            request.setMimeType(mimeType)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "${Consts.DOWNLOADS_SUBPATH}/$fileName"
            )
            request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )

            val id = downloadManager.enqueue(request)
            downloadStatusRepository.addDownload(
                id,
                extractUserNameFromGithubUrl(url),
                fileName,
                DownloadManager.STATUS_RUNNING,
                url
            )
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }

    }

}