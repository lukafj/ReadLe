package com.example.readle.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.navigation.NavDestination
import androidx.navigation.Navigator
import com.example.readle.NavigationItem
import com.example.readle.ReadLeScreen

data class MenuItems(
    val items: List<NavigationItem> = listOf(
        NavigationItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            destination = ReadLeScreen.Home.name
        ),
        NavigationItem(
            title = "Favourite",
            unselectedIcon = Icons.Outlined.Star,
            selectedIcon = Icons.Filled.Star,
            destination = ReadLeScreen.Favourite.name
        ),
        NavigationItem(
            title = "Read books",
            unselectedIcon = Icons.Outlined.CheckCircle,
            selectedIcon = Icons.Filled.CheckCircle,
            destination = ReadLeScreen.ReadBooks.name
        ),
        NavigationItem(
            title = "Account",
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            destination = ReadLeScreen.Account.name
        )
    )

)
