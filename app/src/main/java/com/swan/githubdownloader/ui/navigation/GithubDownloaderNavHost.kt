package com.swan.githubdownloader.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swan.githubdownloader.search.SearchViewModel
import com.swan.githubdownloader.search.model.SearchScreenState
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
            val viewModel: SearchViewModel = hiltViewModel()
            val state: SearchScreenState by viewModel.searchScreenState.collectAsState()
            HomeScreenComposable(state, viewModel::updateSearchQuery, viewModel::searchUserRepos)
        }

    }


}