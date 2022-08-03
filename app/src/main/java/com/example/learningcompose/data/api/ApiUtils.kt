package com.example.learningcompose.data.api

sealed class ApiResult<T: Any> {
    data class Idle<T: Any>(val isIdle: Boolean = true) : ApiResult<T>()
    data class Success<T: Any>(val data: T) : ApiResult<T>()
    data class Error<T: Any>(val errorMessage: String) : ApiResult<T>()
    data class Loading<T: Any>(val isLoading: Boolean = true) : ApiResult<T>()
}