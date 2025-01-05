package com.paulcoding.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.paulcoding.tv.helper.animatedComposable
import com.paulcoding.tv.ui.home.HomePage
import com.paulcoding.tv.ui.player.PlayerPage
import com.paulcoding.tv.ui.theme.TVTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    AppEntry()
                }
            }
        }
    }
}

@Composable
fun AppEntry() {
    val navController = rememberNavController()
    val viewmodel: TVViewModal = viewModel()

    NavHost(navController, startDestination = "home") {
        animatedComposable("home") {
            HomePage(navController, viewmodel)
        }
        animatedComposable("player") {
            PlayerPage(navController, viewmodel)
        }
    }
}

