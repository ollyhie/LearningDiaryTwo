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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.LearningDiary.Navigation
import com.example.LearningDiary.models.Movie
import com.example.LearningDiary.R
import kotlin.random.Random

fun getFavouredIcon(favoured: Boolean): ImageVector {

    if (favoured) {
        return Icons.Filled.Favorite
    }
    return Icons.Outlined.FavoriteBorder
}

@ExperimentalCoilApi
@Composable
fun MovieList(navController: NavController, movies: List<Movie>) {

    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieID ->
                navController.navigate(
                    route = Navigation.DetailScreen.setID(movieID)
                )
            }
        }
    }
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
    // For favour icon
    var favoured by remember {
        mutableStateOf(movie.favoured)
    }
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
                        index = if (expanded) {
                            Random.nextInt(0, movie.images.size)
                        } else {
                            0
                        }
                        changeImage = true
                    }, 6000)
                }
                IconButton(
                    onClick = {
                        favoured = !favoured
                        movie.favoured = favoured
                    }, modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = getFavouredIcon(favoured),
                        contentDescription = "Favour",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(30.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(Color.Black)    //LightGray
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
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.h6.fontSize,
                                color = Color.White,
                                shadow = Shadow(
                                    color = Color.Gray,
                                    offset = Offset(5.0f, 10.0f),
                                    blurRadius = 3f
                                )
                            ),
                            modifier = Modifier
                                .wrapContentSize()
                        )
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Rounded.KeyboardArrowUp,
                            contentDescription = "DropDownArrow",
                            modifier = Modifier.rotate(rotation)
                        )
                    }
                    Column {
                        if (expanded) {
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Year: ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.year)
                                    }
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Actors: ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.actors)
                                    }
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Director: ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.director)
                                    }
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Rating: ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.rating)
                                    }
                                }
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.plot)
                                    }
                                }
                            )
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.Gray)) {
                                        append("Genre: ")
                                    }
                                    withStyle(style = SpanStyle(color = Color.White)) {
                                        append(movie.genre)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}