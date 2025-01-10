package com.yarendemirkaya.fmssbootcampproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yarendemirkaya.cart.ui.CartViewModel
import com.yarendemirkaya.detail.ui.DetailViewModel
import com.yarendemirkaya.favorites.ui.FavoritesViewModel
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.NavGraph
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar.BottomBarItem
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar.DynamicBottomBar
import com.yarendemirkaya.fmssbootcampproject.ui.theme.FMSSBootcampProjectTheme
import com.yarendemirkaya.home.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val visibleList = listOf("home", "favorites", "cart")
        setContent {
            FMSSBootcampProjectTheme {
                val navController = rememberNavController()
                val bottomNavItems = listOf(
                    BottomBarItem("Home", com.yarendemirkaya.home.R.drawable.ic_home, "home"),
                    BottomBarItem(
                        "Favorites",
                        com.yarendemirkaya.home.R.drawable.ic_favorites,
                        "favorites"
                    ),
                    BottomBarItem("Cart", com.yarendemirkaya.home.R.drawable.ic_cart, "cart")
                )
                val bottomBarVisibility =
                    navController.currentBackStackEntryAsState().value?.destination?.route in visibleList


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (bottomBarVisibility) {
                            AnimatedVisibility(bottomBarVisibility) {
                                DynamicBottomBar(
                                    items = bottomNavItems,
                                    navController = navController
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        viewModel = viewModel,
                        navController = navController,
                        detailViewModel = detailViewModel,
                        cartViewModel = cartViewModel,
                        favoritesViewModel = favoritesViewModel
                    )
                }
            }
        }
    }
}

