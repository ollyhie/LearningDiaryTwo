package com.example.learningDiary

import android.os.Handler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import kotlin.random.Random

@ExperimentalCoilApi
@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenAppBar()
        MovieList(navController = navController)
    }
}

@Composable
fun HomeScreenAppBar() {

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
                /*TODO*/
            }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "MenuIcon",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset((-3).dp, 0.dp),
            )
            {

                DropdownMenuItem(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(end = 40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favour",
                        tint = Color.Red
                    )
                    Text(
                        text = "Favorites",
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                }
            }
        }
    )
}


@ExperimentalCoilApi
@Composable
fun MovieRow(movie: Movie, openDetailScreen: (String) -> Unit) {

    // For movie description
    var expanded by remember {
        mutableStateOf(false)
    }
    // For arrow icon
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )
    // For image index
    var index by remember {
        mutableStateOf(0)
    }
    // Allow to change the image
    var changeImage by remember {
        mutableStateOf(false)
    }
    // For delay between images
    val handler = Handler()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { openDetailScreen(movie.id) },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                val painter = rememberImagePainter(
                    data = movie.images[index],
                    builder = {
                        error(R.drawable.avatar2)
                        crossfade(500)
                    }
                )

                val state = painter.state
                if (state is ImagePainter.State.Loading) {
                    CircularProgressIndicator()
                } else if (state is ImagePainter.State.Success) {
                    // Change image
                }

                Image(
                    painter = painter,      //painterResource(id = R.drawable.avatar2),
                    contentDescription = "${movie.title} Image",
                    contentScale = ContentScale.Crop
                )

                if (expanded && changeImage) {

                    changeImage = false
                    handler.postDelayed(Runnable {
                        index = Random.nextInt(0, movie.images.size)
                        changeImage = true
                    }, 6000)
                }
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favour",
                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(30.dp)
                )
            }

            Box(
                modifier = Modifier
                    .background(Color.LightGray)
            ) {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = LinearOutSlowInEasing
                            )
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expanded = !expanded
                                changeImage = expanded
                            }
                    ) {
                        Text(
                            text = movie.title,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .wrapContentSize()
                        )
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowUp,
                            contentDescription = "DropDownArrow",
                            modifier = Modifier.rotate(rotation)
                        )
                    }
                    Column {
                        if (expanded) {
                            Text(
                                text = "Year: ${movie.year}",
                                Modifier.padding(top = 10.dp)
                            )
                            Text(text = "Genre: ${movie.genre}")
                            Text(text = "Actors: ${movie.actors}")
                            Text(text = "Director: ${movie.director}")
                            Text(text = "Rating: ${movie.rating}")
                            Text(text = "Plot: ${movie.plot}")
                        }
                    }
                }

            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieList(navController: NavController) {

    val movies = getMovies()

    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieID ->
                navController.navigate(route = "detailScreen/$movieID")
            }
        }
    }
}