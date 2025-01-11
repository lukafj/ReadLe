package com.example.readle.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.readle.R
import com.example.readle.ReadLeScreen
import com.example.readle.data.Book
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

    LaunchedEffect(Unit) {
        // Search for books in different categories
        viewModel.searchBooks("Horror")
        viewModel.searchBooks("Popular")
        viewModel.searchBooks("Art")
    }

    //Books for you
    val booksForYou by viewModel.books.collectAsState()
    val forYou = booksForYou.take(10)

    //Books popular
    val booksPopular by viewModel.books.collectAsState()
    val popular = booksPopular.take(10)

    //Books recent
    val booksRecent by viewModel.books.collectAsState()
    val recent = booksRecent.take(10)



    //Display with books itself
    Column(
        modifier = Modifier
            .padding(values)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        //display books here
        Text(text = "For you", color = Green01, modifier = Modifier.padding(16.dp))
        //show books
        if (forYou.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(forYou) { book->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                    ) {
                        AsyncImage(
                            model = book.imageUrl,
                            contentDescription = "Book Thumbnail",
                            placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(id = R.drawable.placeholder),
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = book.title ?: "Null", color = Color.Black)
                        Text(text = book.author ?: "Null", color = Beige02)
                    }
                }
            }


            } else {
                Text(text = "Books will be shown here", modifier = Modifier, color = Beige02)
            }




            Text(text = "Popular", color = Green01, modifier = Modifier.padding(16.dp))

            if (popular.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(popular) { book->
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxHeight()
                        ) {
                            AsyncImage(
                                model = book.imageUrl,
                                contentDescription = "Book Thumbnail",
                                placeholder = painterResource(id = R.drawable.placeholder),
                                error = painterResource(id = R.drawable.placeholder),
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = book.title ?: "Null", color = Color.Black)
                            Text(text = book.author ?: "Null", color = Beige02)
                        }
                    }
                }


            } else {
                Text(text = "Books will be shown here", modifier = Modifier, color = Beige02)
            }


            Text(text = "Recent", color = Green01, modifier = Modifier.padding(16.dp))
            if (recent.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(recent) { book->
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxHeight()
                        ) {
                            AsyncImage(
                                model = book.imageUrl,
                                contentDescription = "Book Thumbnail",
                                placeholder = painterResource(id = R.drawable.placeholder),
                                error = painterResource(id = R.drawable.placeholder),
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = book.title ?: "Null", color = Color.Black)
                            Text(text = book.author ?: "Null", color = Beige02)
                        }
                    }
                }


            } else {
                Text(text = "Books will be shown here", modifier = Modifier, color = Beige02)
            }
        }
    }
