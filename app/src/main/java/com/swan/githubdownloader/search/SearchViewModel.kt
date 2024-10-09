package com.swan.githubdownloader.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swan.githubdownloader.domain.find_user_repos.FindUserReposUseCase
import com.swan.githubdownloader.search.model.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val findRepos: FindUserReposUseCase) : ViewModel() {

    private val _searchScreenState = MutableStateFlow(SearchScreenState())
    val searchScreenState = _searchScreenState.asStateFlow()

    fun searchUserRepos() {
        viewModelScope.launch {
            _searchScreenState.update { it.copy(isLoading = true) }
            val repos = findRepos(searchScreenState.value.searchQuery)
            _searchScreenState.update { it.copy(isLoading = false, repos = repos) }
        }
    }

    fun updateSearchQuery(searchQuery: String) {
        _searchScreenState.update {
            it.copy(searchQuery = searchQuery)
        }
    }

}