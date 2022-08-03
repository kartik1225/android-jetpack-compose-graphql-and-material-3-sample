package com.example.learningcompose.ui.homeActivity.tabs.episodesTab

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.learningcompose.data.api.ApiResult
import com.example.learningcompose.data.api.ApiService
import com.example.learningcompose.data.api.model.Episode
import kotlinx.coroutines.flow.MutableStateFlow

class EpisodeVm: ViewModel() {
    var episode = MutableStateFlow<ApiResult<Episode>>(ApiResult.Idle())

    private suspend fun getEpisode(episodeId: String) {
        episode.emit(ApiResult.Loading())

        val result = ApiService().getEpisode(episodeId)

        if(result is ApiResult.Error) {
            Log.i("TAG", "getEpisodes: ${result.errorMessage}")
        }

        episode.emit(result)
    }

}