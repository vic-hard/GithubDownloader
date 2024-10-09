package com.swan.githubdownloader.download

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val getDownloadedReposUseCase: GetDownloadedReposUseCase
) : ViewModel() {

    private val _downloadsScreenState = MutableStateFlow(DownloadsScreenState())
    val downloadsScreenState = _downloadsScreenState.asStateFlow()

    init {
        viewModelScope.launch {
            getDownloadedReposUseCase().collect { downloadedRepos ->
                _downloadsScreenState.update {
                    it.copy(downloadedRepos = downloadedRepos)
                }
            }
        }
    }

}