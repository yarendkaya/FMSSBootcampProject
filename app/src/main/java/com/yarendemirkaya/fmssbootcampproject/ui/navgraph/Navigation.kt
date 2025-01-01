package com.yarendemirkaya.fmssbootcampproject.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.yarendemirkaya.detail.ui.DetailScreen
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.home.ui.HomeScreen
import com.yarendemirkaya.home.ui.HomeViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier, viewModel: HomeViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel = viewModel, navController)
        }
        composable(
            route = "detail/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movie")
            val movie = Gson().fromJson(movieJson, MovieModel::class.java)
            DetailScreen(movie)
        }
    }
}