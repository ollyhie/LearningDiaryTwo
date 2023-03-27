package com.example.hieke_oliver_MAD.uiComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAppBar(title: String, goBack: () -> Unit) {

    TopAppBar(
        backgroundColor = Color(0xff006d65),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                goBack()
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
fun TopAppBar(openFavouritesScreen: () -> Unit) {

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
                        openFavouritesScreen()
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
            }
        }
    )
}