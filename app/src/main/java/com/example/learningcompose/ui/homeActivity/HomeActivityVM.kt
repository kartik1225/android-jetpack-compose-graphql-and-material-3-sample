package com.example.learningcompose.ui.homeActivity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningcompose.R
import com.example.learningcompose.data.api.ApiResult
import com.example.learningcompose.data.api.ApiService
import com.example.learningcompose.data.api.model.Episode
import com.example.learningcompose.ui.homeActivity.tabs.destinations.EpisodesTabDestination
import com.example.learningcompose.ui.homeActivity.tabs.destinations.FavoritesTabDestination
import com.example.learningcompose.ui.homeActivity.tabs.destinations.SettingsTabDestination
import com.example.learningcompose.ui.utils.InitialPaginator
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


enum class HomeBottomNavigationData(
    val direction: DirectionDestinationSpec,
    val title: String,
    val icon: Int,
) {
    Home(EpisodesTabDestination, "Seasons", R.drawable.home),
    Cake(FavoritesTabDestination, "Favorites", R.drawable.cake),
    Account(SettingsTabDestination, "Settings", R.drawable.profile)
}

class HomeActivityVM : ViewModel() {

    var episodePageNumber by mutableStateOf(1)
        private set

    var episodes = MutableStateFlow<ApiResult<List<Episode>>>(ApiResult.Idle())
        private set

    var episodesState by mutableStateOf(EpisodesScreenState())
        private set

    var paginator = InitialPaginator<Int, Episode>(
        initialKey = episodesState.page,
        getNextKey = { episodesState.page + 1 },
        onError = {
            episodesState = episodesState.copy(error = it)
            Log.e("HomeActivityVM", it)
        },
        onSuccess = { episodes, key ->
            episodesState = episodesState.copy(
                items = episodesState.items + episodes,
                page = key,
                endReacted = episodes.isEmpty()
            )
        },
        onLoadUpdated = {
            episodesState = episodesState.copy(isLoading = it)
        },
        onRequest = {
            ApiService().getEpisodes(it)
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextPage()
        }
    }

    init {
        loadNextItems()
    }

    private suspend fun getEpisodes() {
        episodes.emit(ApiResult.Loading())
        val result = ApiService().getEpisodes(episodePageNumber)

        if (result is ApiResult.Error) {
            Log.i(TAG, "getEpisodes: ${result.errorMessage}")
        }

        episodes.tryEmit(result)
    }

    companion object {
        const val TAG = "HomeActivityVm"
    }
}

data class EpisodesScreenState(
    val isLoading: Boolean = false,
    val items: List<Episode> = emptyList(),
    val error: String? = null,
    val endReacted: Boolean = false,
    val page: Int = 1
)


