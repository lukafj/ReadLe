
package com.example.readle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.readle.ui.theme.Beige02
import com.example.readle.ui.theme.Green01

@Composable
fun Home(values: PaddingValues, ) {
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
            Text(text = "For you", color = Green01)
            /*TODO*/
            //show books
            Text(text = "Books will be shown here", modifier = Modifier.align(alignment = Alignment.CenterVertically), color = Beige02)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Recent", color = Green01)
            /*TODO*/
            //show books
            Text(text = "Books will be shown here", modifier = Modifier.align(alignment = Alignment.CenterVertically), color = Beige02)
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Popular", color = Green01)
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
        placeholder = { Text("Search...") },
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