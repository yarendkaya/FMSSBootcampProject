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
import androidx.navigation.compose.rememberNavController
import com.yarendemirkaya.fmssbootcampproject.ui.navgraph.SampleNav
import com.yarendemirkaya.fmssbootcampproject.ui.theme.FMSSBootcampProjectTheme
import com.yarendemirkaya.sample.ui.FloatingActionBtn
import com.yarendemirkaya.sample.ui.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SampleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FMSSBootcampProjectTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionBtn(
                            navController = navController
                        )
                    }
                ) { paddingValues ->

                    SampleNav(
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

