@file:Suppress("INFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION_WARNING")
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.readle

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.readle.data.Book
import com.example.readle.data.BookResponse
import com.example.readle.network.BookApiClient
import com.example.readle.ui.AccountScreen
import com.example.readle.ui.FavouriteScreen
import com.example.readle.ui.ForgotPasswordScreen
import com.example.readle.ui.HomeScreen
import com.example.readle.ui.LoginScreen
import com.example.readle.ui.MenuItems
import com.example.readle.ui.ReadBooksScreen
import com.example.readle.ui.ReadLeViewModel
import com.example.readle.ui.RegisterScreen
import com.example.readle.ui.theme.Beige01
import com.example.readle.ui.theme.Beige02
import com.example.readle.ui.theme.Green01
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

enum class ReadLeScreen() {
    Home,
    Favourite,
    ReadBooks,
    Account,
    Login,
    Register,
    ForgotPassword
}

//change to make it first launch LoginScreen(), from there go to BasicScreen() and from there go to HomeScreen()
//leave NavController here :)
//make internal navigation controller for login, register and forgot password screens

@ExperimentalMaterial3Api
@Composable
fun ReadLeApp(
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
)  {
    //ReadLeApp()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Beige01
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        var searchQuery by rememberSaveable { mutableStateOf("") }  //search query state
        val items = MenuItems().items

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet (
                    drawerContainerColor = Beige01
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Image(
                            /*TODO*/
                            //change to account image later ce bo cajt
                            painter = painterResource(R.drawable.default_avatar_image),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            /*TODO*/
                            //make this display account details (username and mail)
                            Text("<account_name>", fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("<email>", fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.destination)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Beige01,
                                selectedContainerColor = Green01,
                                selectedTextColor = Beige02,
                                selectedIconColor = Beige02
                            )
                        )
                    }
                }
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            SearchBar(
                                query = searchQuery,
                                onQueryChange = { query ->
                                    searchQuery = query
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Green01,
                            titleContentColor = Beige01
                        ),

                        )
                },
                containerColor = Beige01
            ) { values ->
                Column(modifier = Modifier.padding(values)) {
                    if (searchQuery.isNotEmpty()) {
                        BookSearchScreen(
                            viewModel = viewModel,
                            query = searchQuery
                        )
                    } else {
                        //Show books here
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = ReadLeScreen.Login.name
                    //startDestination = ReadLeScreen.Home.name
                ) {
                    composable(route = ReadLeScreen.Login.name) {
                        LoginScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.Register.name) {
                        RegisterScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.ForgotPassword.name) {
                        ForgotPasswordScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.Home.name) {
                        HomeScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.Favourite.name) {
                        FavouriteScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.ReadBooks.name) {
                        ReadBooksScreen(viewModel = viewModel, navController = navController, values = values)
                    }
                    composable(route = ReadLeScreen.Account.name) {
                        AccountScreen(viewModel = viewModel, navController = navController, values = values)
                    }

                }
            }
        }
    }
}



@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit, // Remove @Composable
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange, // Pass the callback directly
        placeholder = { Text("Search") },
        singleLine = true,
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedContainerColor = Beige02,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        }
    )
}



suspend fun fetchBooks(query: String): List<Book> {
    return withContext(Dispatchers.IO) {
        try {
            val response = BookApiClient.bookApiService.retrofitService.searchBooks(query).execute()
            if (response.isSuccessful) {
                response.body()?.books ?: emptyList()
            } else {
                throw Exception("API Error: ${response.code()} ${response.message()}")
            }
        } catch (e: HttpException) {
            throw Exception("Network Error: ${e.message()}")
        } catch (e: Exception) {
            throw Exception("Unexpected Error: ${e.message}")
        }
    }
}


@Composable
fun BookSearchScreen(
    viewModel: ReadLeViewModel = viewModel(),
    query: String
) {
    val books = viewModel.books.collectAsState()

    Column {
        LazyColumn {
            items(books.value) { book ->
                Text(text = "${book.title} by ${book.author}")
            }
        }
    }
}