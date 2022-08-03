package com.example.learningcompose.ui.homeActivity.tabs.episodesTab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.theme.AppTheme
import com.ramcosta.composedestinations.annotation.Destination


data class EpisodeScreenArgs(val episodeId: String, val name: String)

@Destination(
    navArgsDelegate = EpisodeScreenArgs::class
)
@Composable
fun EpisodeScreen(args: EpisodeScreenArgs) {
    Box(Modifier.fillMaxSize()) {
        Column {
            Text(args.episodeId, style = MaterialTheme.typography.labelMedium)
            Text(args.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EpisodeScreenPreview() {
    AppTheme {
        EpisodeScreen(args = EpisodeScreenArgs("1", "Rick and Morty episode!"))
    }
}