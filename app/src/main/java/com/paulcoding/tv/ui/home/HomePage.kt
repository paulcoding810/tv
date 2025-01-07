package com.paulcoding.tv.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.paulcoding.tv.TVViewModal
import kotlinx.coroutines.launch

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, viewModel: TVViewModal) {
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            navController.navigate("player")
        }) {
            Text("View Player")
        }
        Box(modifier = Modifier.clickable {
            scope.launch {
                viewModel.getUrl {
                    navController.navigate("player")
                }
            }
        }) {
            Text("View Player")
        }
    }
}

