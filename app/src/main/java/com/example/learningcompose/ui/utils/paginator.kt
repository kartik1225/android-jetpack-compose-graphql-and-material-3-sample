package com.example.learningcompose.ui.utils

import com.example.learningcompose.data.api.ApiResult

interface Paginator<Key, Type> {
    suspend fun loadNextPage()
    fun reset()
}

class InitialPaginator<Key, Type>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> ApiResult<List<Type>>,
    private inline val onError: suspend (errorMessage: String) -> Unit,
    private inline val onSuccess: suspend (result: List<Type>, newKey: Key) -> Unit,
    private inline val getNextKey: suspend (currentKey: Key) -> Key
) : Paginator<Key, Type> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)

        isMakingRequest = false
        onLoadUpdated(false)

        when (result) {
            is ApiResult.Error -> {
                onError(result.errorMessage)
            }
            is ApiResult.Success -> {
                currentKey = getNextKey(currentKey)
                onSuccess(result.data, currentKey)
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }

}
