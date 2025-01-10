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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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