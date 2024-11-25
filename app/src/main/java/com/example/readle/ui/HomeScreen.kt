@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.readle.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.readle.ReadLeScreen
import com.example.readle.ui.theme.Beige01
import com.example.readle.ui.theme.Beige02
import com.example.readle.ui.theme.Green01
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    values: PaddingValues,
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    
    //Display with books itself
    Column(
        modifier = Modifier
            .padding(values)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        //display books here
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "For you", color = Green01, modifier = Modifier.padding(16.dp))
            /*TODO*/
            //show books
            Text(text = "Books will be shown here", modifier = Modifier.align(alignment = Alignment.CenterVertically), color = Beige02)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Recent", color = Green01, modifier = Modifier.padding(16.dp))
            /*TODO*/
            //show books
            Text(text = "Books will be shown here", modifier = Modifier.align(alignment = Alignment.CenterVertically), color = Beige02)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Popular", color = Green01, modifier = Modifier.padding(16.dp))
            /*TODO*/
            //show books
            Text(text = "Books will be shown here", modifier = Modifier.align(alignment = Alignment.CenterVertically), color = Beige02)
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
    value = query,
    onValueChange = onQueryChange,
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
},


)
}