package com.paulcoding.tv.ui.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.tv.material3.Text
import com.paulcoding.tv.TVViewModal

@Composable
fun PlayerPage(navController: NavController, viewModel: TVViewModal) {
    val uiState by viewModel.stateFlow.collectAsState()

    uiState.videoUrl.run {
        if (this == null)
            Text("No video url")
        else
            VideoPlayer(
                videoUri = this,
                modifier = Modifier.fillMaxSize()
            )
    }
}