package com.yarendemirkaya.fmssbootcampproject.ui.navgraph.bottombar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yarendemirkaya.fmssbootcampproject.R


@Composable
fun DynamicBottomBar(navController: NavController) {
    val bottomNavItems = listOf(
        BottomBarItem("Home", R.drawable.ic_home, "home"),
        BottomBarItem("Favorites", R.drawable.ic_favorites, "favorites"),
        BottomBarItem("My Cart", R.drawable.ic_cart, "cart")
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Black,
        contentColor = Color(0xFFFFA500)
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentDestination?.route == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentDestination?.route != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFA500),
                    selectedTextColor = Color(0xFFFFA500),
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color(0xFFECBC77),
                    unselectedTextColor = Color(0xFFECBC77)
                ),
                label = {
                    Text(
                        text = item.title,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}


