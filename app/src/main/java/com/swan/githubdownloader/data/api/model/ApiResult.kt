package com.swan.githubdownloader.data.api.model

sealed class ApiResult<out T> {
    object Loading : ApiResult<Unit>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
}