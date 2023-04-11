package com.example.learningDiary.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.learningDiary.models.Genre
import com.example.learningDiary.models.Movie
import com.example.learningDiary.models.getMovies
import java.time.LocalDateTime

class MoviesViewModel: ViewModel() {

    private val _movies = getMovies().toMutableStateList()
    val movies: List<Movie>
        get() = _movies

    private val _favouredMovies = mutableStateListOf<Movie>()
    val favouredMovies: List<Movie>
        get() {
            if (_favouredMovies.isEmpty())
                updateFavouredMovies()
            return _favouredMovies
        }

    fun getMovieByID(movieID: String?) : Movie? {

        for (movie in getMovies()) {
            if (movie.id == movieID) return movie
        }
        return null
    }

    // Favour/un-favour a movie
    fun toggleFavourite(movieID: String) {
        for (listMovie in _movies) {
            if (listMovie.id == movieID) {
                val index = _movies.indexOf(listMovie)
                val mov: Movie = listMovie.copy()
                mov.favoured = !mov.favoured
                _movies[index] = mov
                updateFavouredMovies()
                return
            }
        }
    }

    private fun updateFavouredMovies() {
        _favouredMovies.clear()
        for (movie in _movies) {
            if (movie.favoured) {
                _favouredMovies.add(movie)
            }
        }
    }

    // Get the index of a movie inside the list
    private fun getMovieIndex(movieID: String) : Int {

        for ((index, movie) in _movies.withIndex()) {
            if (movie.id == movieID) {
                return index
            }
        }
        return -1
    }

    // Add a new movie
    fun addMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, rating: Float) {

        _movies.add(
            Movie(
                id =  title.replace(" ", "") + LocalDateTime.now().toString(),
                title = title,
                year = year,
                genres = genres,
                director = director,
                actors = actors,
                plot = plot,
                images = listOf(
                    "https://thumbs.dreamstime.com/b/happy-cat-closeup-portrait-funny-smile-cardboard-young-blue-background-102078702.jpg"
                ),
                rating = rating
            )
        )
    }
}