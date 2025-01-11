package com.yarendemirkaya.fmssbootcampproject.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.NavGraph
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar.DynamicBottomBar
import com.yarendemirkaya.fmssbootcampproject.ui.theme.FMSSBootcampProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FMSSBootcampProjectTheme {
                val navController = rememberNavController()
                val visibleList = listOf("home", "favorites", "cart")
                val bottomBarVisibility =
                    navController.currentBackStackEntryAsState().value?.destination?.route in visibleList

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(bottomBarVisibility) {
                            DynamicBottomBar(
                                navController = navController
                            )
                        }
                    }
                ) { paddingValues ->
                    NavGraph(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        navController = navController,
                    )
                }
            }
        }
    }
}

