package com.example.learningcompose.ui.homeActivity.tabs

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learningcompose.data.api.ApiResult
import com.example.learningcompose.data.api.model.Episode
import com.example.learningcompose.data.api.model.sampleEpisode
import com.example.learningcompose.ui.episodeActivity.EpisodeActivity
import com.example.learningcompose.ui.homeActivity.HomeActivityVM
import com.example.learningcompose.ui.theme.AppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable()
fun EpisodesTab(vm: HomeActivityVM = viewModel(), navController: NavController) {
    val state = vm.episodesState

    Log.i("TAG", "EpisodesTab: ${state.items.size}, isLoading: ${state.isLoading}")

    if (state.error != null && state.items.isEmpty()) {
        Scaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Text(state.error)
            }
        }
    }

    val episodes = state.items
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(episodes.size) { i ->
            val episode = episodes[i]

            if (i >= state.items.size - 1 && !state.endReacted && !state.isLoading) {
                vm.loadNextItems()
            }

            EpisodeCard(episode) {
                Log.i("EpisodeCard", "EpisodesTab: ${episode.episode}")
                val intent = Intent(context, EpisodeActivity::class.java)
                intent.putExtra("episodeId", episode.id)
                context.startActivity(intent)
            }
        }

        if (state.isLoading) {
            val shimmerCount = if (episodes.isEmpty()) 10 else 1
            items(shimmerCount) {
                EpisodeCardShimmer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EpisodeCard(episode: Episode, onSelected: (episode: Episode) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        )
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    episode.id,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    episode.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    episode.episode,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onSelected(episode) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "View more",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EpisodeCardShimmer() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Preview()
@Composable
fun EpisodeCardPreview() {
    AppTheme(useDarkTheme = true) {
        EpisodeCard(sampleEpisode(),{})
    }
}

@Preview()
@Composable
fun EpisodeCardSimmerPreview() {
    AppTheme(useDarkTheme = true) {
        EpisodeCardShimmer()
    }
}