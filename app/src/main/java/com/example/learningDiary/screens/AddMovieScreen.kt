package com.example.learningDiary.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learningDiary.R
import com.example.learningDiary.models.Genre
import com.example.learningDiary.models.ListItemSelectable
import com.example.learningDiary.uiComponents.SimpleAppBar
import com.example.learningDiary.viewModel.MoviesViewModel


@Composable
fun AddMovieScreen(navController: NavController, moviesViewModel: MoviesViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleAppBar(title = "New Movie", onBackClicked = { navController.navigateUp() })
        },
    ) { padding ->
        MainContent(
            moviesViewModel = moviesViewModel,
            modifier = Modifier.padding(padding),
            onAddClicked = { navController.navigateUp() }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(moviesViewModel: MoviesViewModel, modifier: Modifier = Modifier, onAddClicked: () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var errorInTitle by remember {
                mutableStateOf(false)
            }
            var errorInYear by remember {
                mutableStateOf(false)
            }
            var errorInGenres by remember {
                mutableStateOf(false)
            }
            var errorInDirector by remember {
                mutableStateOf(false)
            }
            var errorInActors by remember {
                mutableStateOf(false)
            }
            var errorInRating by remember {
                mutableStateOf(false)
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    title = it
                    errorInTitle = title.isBlank()},
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = errorInTitle,

            )
            if (errorInTitle) {
                Text(
                    text = "You must enter a title",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }
            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    year = it
                    errorInYear = year.isBlank() },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = errorInYear
            )
            if (errorInYear) {
                Text(
                    text = "You must enter a year",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            errorInGenres = !genreItems.any { it.isSelected }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }
            if (errorInGenres) {
                Text(
                    text = "You must select at least one genre",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    director = it
                    errorInDirector = director.isBlank() },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = errorInDirector
            )
            if (errorInDirector) {
                Text(
                    text = "You must select a director",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    actors = it
                    errorInActors = actors.isBlank() },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = errorInActors
            )
            if (errorInActors) {
                Text(
                    text = "You must select at least one actor",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = false
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                    errorInRating = !rating.matches(("\\d.?\\d?\\d?").toRegex())
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = errorInRating
            )
            if (errorInRating) {
                Text(
                    text = "You must enter a rating",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            isEnabledSaveButton = (
                    !errorInTitle && title.isNotEmpty() &&
                            !errorInYear && year.isNotEmpty() &&
                            !errorInGenres && genreItems.any { it.isSelected } &&
                            !errorInDirector && director.isNotEmpty() &&
                            !errorInActors && actors.isNotEmpty() &&
                            !errorInRating && rating.matches(("\\d.?\\d?\\d?").toRegex())
                    )
            Button(
                enabled = isEnabledSaveButton,
                onClick = {
                    moviesViewModel.addMovie(
                        title = title,
                        year = year,
                        genres = genres.filter { genre ->
                                genreItems.filter { item -> item.isSelected }
                                    .map { selectedItems -> selectedItems.title }
                                    .contains(genre.name)
                            },
                        director = director,
                        actors = actors,
                        plot = plot,
                        rating = rating.replace(",",".").toFloat()
                    )
                    onAddClicked()

                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}