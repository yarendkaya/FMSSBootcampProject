package com.yarendemirkaya.fmssbootcampproject.ui.navgraph

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.yarendemirkaya.cart.ui.CartScreen
import com.yarendemirkaya.cart.ui.CartViewModel
import com.yarendemirkaya.detail.ui.DetailScreen
import com.yarendemirkaya.detail.ui.DetailViewModel
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.favorites.ui.FavoritesScreen
import com.yarendemirkaya.favorites.ui.FavoritesViewModel
import com.yarendemirkaya.home.ui.HomeScreen
import com.yarendemirkaya.home.ui.HomeViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            val viewModel = hiltViewModel<HomeViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            LaunchedEffect(Unit) {
                viewModel.fetchMovies()
            }

            HomeScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateToDetail = { movie ->
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("detail/$movieJson")
                }
            )
        }

        composable(
            route = "detail/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) { backStackEntry ->

            val movieJson = backStackEntry.arguments?.getString("movie")
            val movie = Gson().fromJson(movieJson, MovieModel::class.java)


            val viewModel = hiltViewModel<DetailViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.checkIfMovieIsFavorited(movie.name)
            }

            DetailScreen(
                uiState = uiState,
                movie = movie,
                onAction = viewModel::onAction,
                onToggleFavorite = {
                    if (uiState.isFavorited) {
                        viewModel.deleteMovieFromFavorites(movie)
                    } else {
                        viewModel.addMovieToFavorites(movie)
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("cart") {
            val viewModel = hiltViewModel<CartViewModel>()

            CartScreen()
        }

        composable("favorites") {
            val viewModel = hiltViewModel<FavoritesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            LaunchedEffect(Unit) {
                viewModel.getFavorites()
            }

            FavoritesScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateToDetailFromFavorites = { movie ->
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("detail/$movieJson")
                }
            )
        }
    }
}