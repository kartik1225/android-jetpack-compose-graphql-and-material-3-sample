package com.example.learningcompose.ui.episodeActivity

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learningcompose.data.api.ApiResult
import com.example.learningcompose.data.api.ApiService
import com.example.learningcompose.data.api.model.Character
import com.example.learningcompose.data.api.model.Episode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class EpisodeVmFactory(private val episodeId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeVm::class.java)) {
            return EpisodeVm(episodeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class EpisodeVm(val episodeId: String) : ViewModel() {
    var episode = MutableStateFlow<ApiResult<Episode>>(ApiResult.Idle())

    var selectedCharacter by mutableStateOf<Character?>(null)

    init {
        getEpisode(episodeId)
    }

    fun getEpisode(episodeId: String) {
        viewModelScope.launch {
            episode.emit(ApiResult.Loading())
            Log.d("EpisodeVm", "getting episode $episodeId")
            val result = ApiService().getEpisode(episodeId)

            if (result is ApiResult.Error) {
                Log.i("EpisodeVm", "getEpisodes: ${result.errorMessage}")
            }

            episode.emit(result)
        }
    }
}