package com.example.learningcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.learningcompose.R
import com.example.learningcompose.data.api.model.Character
import com.example.learningcompose.data.api.model.sampleCharacter
import com.example.learningcompose.ui.theme.AppTheme
import com.example.learningcompose.ui.theme.Spacer

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: Character,
    onMoreDetailsClick: (character: Character) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
    ) {
        Box(
            Modifier
                .padding(12.dp)
                .clip(MaterialTheme.shapes.large)
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current).data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier.aspectRatio(1f)
            )
        }
        Spacer.Small()
        Text(
            character.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer.Small()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            Icon(
//                imageVector = Icons.Rounded.LocationOn,
//                contentDescription = "Location",
//                tint = MaterialTheme.colorScheme.onSurfaceVariant,
//                modifier = Modifier
//                    .size(16.dp)
//                    .padding(end = 2.dp)
//            )
            Text(
                character.location.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer.Small()
        Button(
            onClick = { onMoreDetailsClick(character) }) {
            Text("More details")
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "Forward",
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun CharacterCardFullHorizontal(modifier: Modifier = Modifier, character: Character) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(12.dp)
                .clip(MaterialTheme.shapes.large)
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current).data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                modifier = Modifier.aspectRatio(1f)
            )
        }
        Spacer.Small()
        Column(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(end = 12.dp)),
        ) {
            Row {
                Text(
                    text = "${character.name} (${character.status})",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                )

            }
            Spacer.Small()
            Column() {
                Text(
                    "Location: ${character.location.name}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "Gender: ${character.gender}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "Species: ${character.species}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun CharacterCardPreview() {
    AppTheme {
        CharacterCard(character = sampleCharacter(), onMoreDetailsClick = {})
    }
}

@Preview
@Composable
fun CharacterCardFullHorizontalPreview() {
    AppTheme {
        CharacterCardFullHorizontal(
            character = sampleCharacter(),
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
        )
    }
}