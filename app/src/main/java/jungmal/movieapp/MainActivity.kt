package jungmal.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import jungmal.movieapp.features.detail.presentation.screen.DetailScreenRoute
import jungmal.movieapp.features.feed.presentation.screen.FeedScreen
import jungmal.movieapp.ui.theme.MovieAppTheme
import jungmal.movieapp.ui.theme.currentColorScheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.currentColorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(padding)
        ) {
            composable("main") {
                FeedScreen { title -> navController.navigate("detail/$title") }
            }

            composable(
                "detail/{title}",
                arguments = listOf(navArgument("title") {type = NavType.StringType})
                ) { navBackStackEntry ->
                DetailScreenRoute(
                    title = navBackStackEntry.arguments?.getString("title") ?: "",
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}