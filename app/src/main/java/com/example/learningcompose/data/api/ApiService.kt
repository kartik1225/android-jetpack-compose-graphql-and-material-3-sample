package com.example.learningcompose.data.api

import com.example.learningcompose.EpisodeQuery
import com.example.learningcompose.EpisodesQuery
import com.example.learningcompose.data.api.model.Episode

class ApiService {
    suspend fun getEpisodes(page: Int): ApiResult<List<Episode>> {
        return try {
            val query = ApiClient.apolloClient().query(EpisodesQuery(page)).execute()
            val data = query.data?.episodes
            if (data?.results != null) {
                val episodes = data.results.map { Episode(it!!.episode) }
                ApiResult.Success(episodes)
            } else {
                ApiResult.Error("No episodes found!")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getEpisode(id: String): ApiResult<Episode> {
        return try {
            val query = ApiClient.apolloClient().query(EpisodeQuery(id)).execute()
            val data = query.data?.episode
            if (data?.episode != null) {
                val episode = Episode(data.episode)
                ApiResult.Success(episode)
            } else {
                ApiResult.Error("No episodes found!")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unknown error")
        }
    }
}
