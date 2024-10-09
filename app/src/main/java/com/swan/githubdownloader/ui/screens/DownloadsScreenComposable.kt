package com.swan.githubdownloader.ui.screens

import com.swan.githubdownloader.ui.views.DownloadedRepoItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swan.githubdownloader.R
import com.swan.githubdownloader.download.model.DownloadsScreenState
import com.swan.githubdownloader.ui.views.DownloadingRepoItem


@Composable
@Preview
fun DownloadsScreenComposablePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        DownloadsScreenComposable(
            state = DownloadsScreenState(listOf())
        )
    }
}

@Composable
fun DownloadsScreenComposable(state: DownloadsScreenState) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = stringResource(R.string.downloading_repositories),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (state.downloadingRepos.isEmpty()) {
            Text(
                text = stringResource(R.string.no_downloading_repositories),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            LazyColumn {
                items(
                    items = state.downloadingRepos,
                    key = { repo -> repo.repositoryName }
                ) { repo ->
                    DownloadingRepoItem(userName = repo.userName, repoName = repo.repositoryName)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.downloaded_repositories),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (state.downloadedRepos.isEmpty()) {
            Text(
                text = stringResource(R.string.no_downloaded_repositories),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            LazyColumn {
                items(
                    items = state.downloadedRepos,
                    key = { repo -> repo.id }
                ) { repo ->
                    DownloadedRepoItem(userName = repo.userName, repoName = repo.repoName)
                }
            }
        }
    }

}