package com.example.readle.ui

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
