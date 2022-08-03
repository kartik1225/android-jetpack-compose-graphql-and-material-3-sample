package com.example.learningcompose.ui.episodeActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.lifecycle.ViewModelProvider
import com.example.learningcompose.ui.theme.AppTheme

class EpisodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val episodeId: String = intent.getStringExtra("episodeId") ?: "1"
        // for initial load
        ViewModelProvider(this,EpisodeVmFactory(episodeId))[EpisodeVm::class.java]
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    EpisodeScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AppTheme {
        EpisodeScreen()
    }
}