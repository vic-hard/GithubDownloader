package com.swan.githubdownloader.data.repository

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.swan.githubdownloader.data.api.model.ApiResult
import com.swan.githubdownloader.util.Consts
import timber.log.Timber
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val context: Context
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

            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }

    }

}