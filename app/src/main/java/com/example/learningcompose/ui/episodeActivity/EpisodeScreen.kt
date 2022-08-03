package com.example.learningcompose.ui.episodeActivity

import android.app.Activity
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningcompose.data.api.ApiResult
import com.example.learningcompose.data.api.model.Character
import com.example.learningcompose.data.api.model.Episode
import com.example.learningcompose.data.api.model.sampleEpisode
import com.example.learningcompose.ui.components.AppBarBackButton
import com.example.learningcompose.ui.components.AppBottomSheetScaffold
import com.example.learningcompose.ui.components.CharacterCard
import com.example.learningcompose.ui.components.CharacterCardFullHorizontal
import com.example.learningcompose.ui.theme.Spacer
import com.example.learningcompose.ui.theme.AppTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.rememberTopAppBarScrollState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun EpisodeScreen(vm: EpisodeVm = viewModel()) {
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val context = LocalContext.current as Activity


    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = MaterialTheme.shapes.large.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
        sheetContent = {
            AppBottomSheetScaffold {
                vm.selectedCharacter?.let {
                    CharacterCardFullHorizontal(character = it, modifier = Modifier.height(120.dp))
                } ?: Box {}
            }
        },
    ) {
        Content(bottomSheetScaffoldState = bottomSheetScaffoldState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    vm: EpisodeVm = viewModel(),
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val scope = rememberCoroutineScope()

    when (val state = vm.episode.collectAsState().value) {
        is ApiResult.Idle -> Box {}
        is ApiResult.Error -> Box(Modifier.fillMaxWidth()) {
            Text(state.errorMessage)
        }
        is ApiResult.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is ApiResult.Success -> {
            EpisodeSuccessScreen(episode = state.data, onCharacterClick = { character ->
                vm.selectedCharacter = character
                scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EpisodeSuccessScreen(
    episode: Episode,
    onCharacterClick: (character: Character) -> Unit = {}
) {
    val context = LocalContext.current
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarScrollState()
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                actions = {
                    Box(
                        Modifier
                            .padding(end = 8.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
                            .padding(horizontal = 18.dp, vertical = 4.dp)
                    ) {
                        Text(episode.episode, style = MaterialTheme.typography.bodyMedium)
                    }
                },
                title = {
                    Text(episode.name)
                },
                navigationIcon = { AppBarBackButton { (context as Activity).onBackPressed() } },
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 180.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp),
                    userScrollEnabled = true
                ) {
                    items(episode.characters.size) {
                        CharacterCard(
                            character = episode.characters[it],
                            onMoreDetailsClick = onCharacterClick
                        )
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EpisodeScreenSuccessPreview() {
    AppTheme(useDarkTheme = true) {
        EpisodeSuccessScreen(sampleEpisode())
    }
}
