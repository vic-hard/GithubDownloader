package com.swan.githubdownloader.ui.screens

import DownloadedRepoItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swan.githubdownloader.download.model.DownloadsScreenState


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
            text = "Downloaded Repositories",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(
                items = state.downloadedRepos,
                key = { repo ->
                    repo.id
                }
            ) { repo ->
                DownloadedRepoItem(userName = repo.userName, repoName = repo.repoName)
            }
        }
    }

}