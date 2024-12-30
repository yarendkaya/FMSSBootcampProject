package com.yarendemirkaya.fmssbootcampproject.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yarendemirkaya.home.ui.HomeScreen
import com.yarendemirkaya.home.ui.HomeViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier, viewModel: HomeViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel = viewModel)
        }
    }
}