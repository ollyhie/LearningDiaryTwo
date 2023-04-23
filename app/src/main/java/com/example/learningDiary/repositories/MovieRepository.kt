package com.example.learningDiary.repositories

import com.example.learningDiary.data.MovieDao
import com.example.learningDiary.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie = movie)

    suspend fun update(movie: Movie) = movieDao.update(movie = movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie = movie)

    fun getAllMovies() = movieDao.readAll()

    fun getAllFavoured() = movieDao.readAllFavoured()

    fun getMovieByID(movieID: String?) = movieDao.getMovieByID(movieID = movieID)
}