package com.swan.githubdownloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.swan.githubdownloader.data.repository.DownloadStatusRepository
import com.swan.githubdownloader.domain.downloads.SaveDownloadedRepoUseCase
import com.swan.githubdownloader.util.Consts
import com.swan.githubdownloader.util.extractUserNameFromGithubUrl
import timber.log.Timber

class DownloadReceiver(
    private val downloadManager: DownloadManager,
    private val saveDownloadedRepository: SaveDownloadedRepoUseCase,
    private val downloadStatusRepository: DownloadStatusRepository
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val downloadId = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1) ?: -1
        if (downloadId != -1L) {
            handleDownloadCompleted(downloadId)
        }
    }

    private fun handleDownloadCompleted(downloadId: Long) {
        val query = DownloadManager.Query().setFilterById(downloadId)
        val cursor = downloadManager.query(query)
        if (cursor.moveToFirst()) {
            val statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
            val fileNameColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)
            val urlNameColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_URI)

            val status = cursor.getInt(statusColumnIndex)
            val fileName = cursor.getString(fileNameColumnIndex)
            val url = cursor.getString(urlNameColumnIndex)
            val userName = extractUserNameFromGithubUrl(url)

            // not a GitHub download link
            if (!url.contains(Consts.GITHUB_DOWNLOAD_LINK_PART))
                return

            when (status) {
                DownloadManager.STATUS_SUCCESSFUL -> {
                    saveDownloadedRepository(userName, fileName)
                    downloadStatusRepository.removeDownload(downloadId)
                }
                DownloadManager.STATUS_RUNNING, DownloadManager.STATUS_PENDING -> {
                    downloadStatusRepository.addDownload(downloadId, userName, fileName, status, url)
                }
            }
        }
        cursor.close()
    }

}