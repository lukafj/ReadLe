@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@file:Suppress("INFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION_WARNING")

package com.example.readle

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readle.ui.FavouriteScreen
import com.example.readle.ui.HomeScreen
import com.example.readle.ui.MenuItems
import com.example.readle.ui.ReadLeViewModel
import com.example.readle.ui.SearchBar
import com.example.readle.ui.theme.Beige01
import com.example.readle.ui.theme.Beige02
import com.example.readle.ui.theme.Green01
import kotlinx.coroutines.launch

enum class ReadLeScreen() {
    Home,
    Favourite,
    YourBooks,
    Account
}


@Composable
fun ReadLeApp(
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
)  {

}