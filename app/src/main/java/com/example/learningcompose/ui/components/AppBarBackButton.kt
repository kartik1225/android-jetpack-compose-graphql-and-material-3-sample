package com.example.learningcompose.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun AppBarBackButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
    }
}