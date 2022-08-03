package com.example.learningcompose.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Spacer {
    companion object {
        @Composable
        fun Small() = Spacer(Modifier.padding(all = 4.dp))

        @Composable
        fun Medium() = Spacer(Modifier.padding(all = 8.dp))

        @Composable
        fun Large() = Spacer(Modifier.padding(all = 16.dp))
    }
}
