package com.yarendemirkaya.fmssbootcampproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.yarendemirkaya.fmssbootcampproject.R
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.NavGraph
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.SampleNav
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar.BottomBarItem
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar.DynamicBottomBar
import com.yarendemirkaya.fmssbootcampproject.ui.theme.FMSSBootcampProjectTheme
import com.yarendemirkaya.home.ui.HomeViewModel
import com.yarendemirkaya.home.ui.components.CustomTopAppBar
import com.yarendemirkaya.sample.ui.FloatingActionBtn
import com.yarendemirkaya.sample.ui.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //    private val viewModel: SampleViewModel by viewModels()
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FMSSBootcampProjectTheme {
                val bottomNavItems = listOf(
                    BottomBarItem(
                        "Home",
                        com.yarendemirkaya.home.R.drawable.ic_home,
                        "home"
                    ),
                    BottomBarItem(
                        "Favorites",
                        com.yarendemirkaya.home.R.drawable.ic_favorites,
                        "favorites"
                    ),
                    BottomBarItem(
                        "Cart",
                        com.yarendemirkaya.home.R.drawable.ic_cart,
                        "cart"
                    )
                )
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        DynamicBottomBar(
                            items = bottomNavItems,
                            navController = navController
                        )
                    }

//                    floatingActionButton = {
//                        FloatingActionBtn(
//                            navController = navController
//                        )
//                    }
                ) { paddingValues ->

//                    SampleNav(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(paddingValues),
//                        viewModel = viewModel,
//                        navController = navController
//                    )
                    NavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

