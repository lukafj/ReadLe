package com.example.readle

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: String
)
