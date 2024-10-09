package com.swan.githubdownloader.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swan.githubdownloader.R
import com.swan.githubdownloader.data.api.model.GithubRepo
import com.swan.githubdownloader.search.model.SearchScreenState
import com.swan.githubdownloader.ui.views.RepoItem
import com.swan.githubdownloader.util.Consts

@Composable
@Preview
fun HomeScreenComposablePreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        HomeScreenComposable(
            state = SearchScreenState(),
            updateSearchQuery = {},
            searchUserRepos = {},
            downloadRepo = { _, _ -> },
            navigateToDownloadsScreen = {}
        )
    }
}

@Composable
fun HomeScreenComposable(
    state: SearchScreenState,
    updateSearchQuery: (username: String) -> Unit,
    searchUserRepos: () -> Unit,
    downloadRepo: (repo: GithubRepo, mimeType: String) -> Unit,
    navigateToDownloadsScreen: () -> Unit
) {
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(id = R.string.github_username)) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier
                    .weight(1f)
                    .size(56.dp)
                    .clickable {
                        navigateToDownloadsScreen.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_download_24dp),
                contentDescription = "Downloads"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            updateSearchQuery(username)
            searchUserRepos()
        }) {
            Text(stringResource(id = R.string.search))
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(
                    items = state.repos,
                    key = { repo ->
                        repo.name
                    }
                ) { repo ->
                    RepoItem(repo, downloadRepo = { downloadRepo(repo, Consts.ZIP_MIME_TYPE) })
                }
            }
        }
    }
}