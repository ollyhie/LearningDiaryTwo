package com.example.learningDiary.uiComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAppBar(title: String, onBackClicked: () -> Unit) {

    TopAppBar(
        backgroundColor = Color(0xff006d65),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackClicked()
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
    )
}

@Composable
fun TopAppBar(onOpenFavouritesClicked: () -> Unit, onAddMovieClicked: () -> Unit) {

    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        backgroundColor = Color(0xff006d65),
        title = {
            Text(text = "Movies")
        },
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "MenuIcon",
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset((-3).dp, 0.dp),
            )
            {

                DropdownMenuItem(
                    onClick = {
                        onOpenFavouritesClicked()
                    },
                    modifier = Modifier
                        .padding(end = 40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "FavourIcon",
                        tint = Color.Red
                    )
                    Text(
                        text = "Favourites",
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                }

                DropdownMenuItem(
                    onClick = {
                        onAddMovieClicked()
                    },
                    modifier = Modifier
                        .padding(end = 40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "AddIcon",
                        tint = Color.DarkGray
                    )
                    Text(
                        text = "Add Movie",
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                }
            }
        }
    )
}