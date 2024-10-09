package com.swan.githubdownloader.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swan.githubdownloader.ui.navigation.destinations.HomeScreen
import com.swan.githubdownloader.ui.navigation.destinations.SplashScreen
import com.swan.githubdownloader.ui.screens.HomeScreenComposable
import com.swan.githubdownloader.ui.screens.SplashScreenComposable

@Composable
internal fun GithubDownloaderNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SplashScreen,
    ) {

        composable<SplashScreen> {
            SplashScreenComposable {
                navController.navigate(HomeScreen)
            }
        }

        composable<HomeScreen> {
            HomeScreenComposable()
        }

    }


}