package com.swan.githubdownloader.download

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swan.githubdownloader.domain.downloads.GetCurrentDownloadsUseCase
import com.swan.githubdownloader.domain.downloads.GetDownloadedReposUseCase
import com.swan.githubdownloader.download.model.DownloadsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val getDownloadedReposUseCase: GetDownloadedReposUseCase,
    private val getCurrentDownloads: GetCurrentDownloadsUseCase
) : ViewModel() {

    private val _downloadsScreenState = MutableStateFlow(DownloadsScreenState())
    val downloadsScreenState = _downloadsScreenState.asStateFlow()

    init {
        updateDownloadedReposState()
        updateDownloadingReposState()
    }

    private fun updateDownloadedReposState() {
        viewModelScope.launch {
            getDownloadedReposUseCase().collect { downloadedRepos ->
                _downloadsScreenState.update {
                    it.copy(downloadedRepos = downloadedRepos)
                }
            }
        }
    }

    private fun updateDownloadingReposState() {
        viewModelScope.launch {
            getCurrentDownloads().collect { downloadingMap ->
                _downloadsScreenState.update { state ->
                    val sortedDownloadingRepos = downloadingMap.values
                        .sortedBy { it.repositoryName }
                    state.copy(downloadingRepos = sortedDownloadingRepos)
                }
            }
        }
    }

}