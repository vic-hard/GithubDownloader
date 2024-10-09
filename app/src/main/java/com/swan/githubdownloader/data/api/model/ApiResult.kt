package com.swan.githubdownloader.data.api.model

sealed class ApiResult<out T> {
    object Loading : ApiResult<Unit>()
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
    data class Failure<T>(val data: T, val message: String) : ApiResult<T>()

    fun isSuccess(): Boolean {
        return this is Success<*>
    }

    fun getSuccessData(): T? {
        return (this as? Success<T>)?.data
    }
}