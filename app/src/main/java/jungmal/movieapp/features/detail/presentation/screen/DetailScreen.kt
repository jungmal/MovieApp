package jungmal.movieapp.features.detail.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = title)
    }
}