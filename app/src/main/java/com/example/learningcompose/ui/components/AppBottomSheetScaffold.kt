package com.example.learningcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.AppTheme

@Composable
fun AppBottomSheetScaffold(modifier: Modifier = Modifier, content: @Composable() () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(24.dp), contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .height(4.dp)
                        .width(68.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }
            content()
        }
    }
}


@Preview
@Composable
fun AppBottomSheetScaffoldPreview() {
    AppTheme {
        AppBottomSheetScaffold {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .height(100.dp)
            ) {
                Text("Bottom Sheet")
            }
        }
    }
}