package com.swan.githubdownloader.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swan.githubdownloader.R
import com.swan.githubdownloader.data.api.model.GithubRepo

@Composable
fun RepoItem(repo: GithubRepo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val uriHandler = LocalUriHandler.current
        Column(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    if (repo.htmlUrl.isNotBlank())
                        uriHandler.openUri(repo.htmlUrl)
                }
        ) {
            Text(text = repo.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = repo.description ?: stringResource(id = R.string.no_description), style = MaterialTheme.typography.bodyMedium)
            Text(text = repo.language ?: stringResource(id = R.string.no_language))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                // Handle zip download (implement download logic here)
            }) {
                Text(text = stringResource(id = R.string.download_zip))
            }
        }
    }
}