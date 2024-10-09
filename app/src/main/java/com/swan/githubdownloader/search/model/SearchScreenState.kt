package com.swan.githubdownloader.search.model

import com.swan.githubdownloader.data.api.model.GithubRepo

data class SearchScreenState(
    val searchQuery: String = "",
    val repos: List<GithubRepo> = listOf(),
    val isLoading: Boolean = false
)
