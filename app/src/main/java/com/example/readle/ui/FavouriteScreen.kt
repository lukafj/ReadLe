package com.example.readle.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.readle.ui.theme.Beige02


@Composable
fun FavouriteScreen(
    values: PaddingValues,
    viewModel: ReadLeViewModel = viewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(values)
    ) {
        Text(
            text = "Your books",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            color = Beige02
        )
    }

}