package com.example.learningDiary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningDiary.models.Movie
import com.example.learningDiary.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _favouredMovies = MutableStateFlow(listOf<Movie>())
    val favouredMovies: StateFlow<List<Movie>> = _favouredMovies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect { movieList ->
                if (movieList != null)
                    _movies.value = movieList
            }
        }
        viewModelScope.launch {
            repository.getAllFavoured().collect { movieList ->
                if (movieList != null)
                    _favouredMovies.value = movieList
            }
        }
    }

    fun getMovieByID(movieID: String?) : Movie {
        return repository.getMovieByID(movieID = movieID)
    }

    suspend fun toggleFavourite(movie: Movie) {
        movie.favoured = !movie.favoured
        repository.update(movie)
    }

    // Add a new movie
    suspend fun addMovie(movie: Movie) {
        repository.add(movie = movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        repository.delete(movie = movie)
    }
}