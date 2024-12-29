package com.yarendemirkaya.fmssbootcampproject.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yarendemirkaya.sample.ui.SampleAddPersonScreen
import com.yarendemirkaya.sample.ui.SampleHomeScreen
import com.yarendemirkaya.sample.ui.SampleViewModel

@Composable
fun SampleNav(
    modifier: Modifier = Modifier, viewModel: SampleViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SampleHomeScreen(viewModel = viewModel)
        }
        composable("add") {
            SampleAddPersonScreen(viewModel = viewModel, navController)
        }
    }
}