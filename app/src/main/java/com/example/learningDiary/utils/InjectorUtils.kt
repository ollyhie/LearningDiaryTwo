package com.example.learningDiary.utils

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import com.example.learningDiary.MoviesViewModelFactory
import com.example.learningDiary.data.MovieDataBase
import com.example.learningDiary.repositories.MovieRepository

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDataBase.getDatabase(context).movieDao())
    }

    fun provideMoviesViewModelFactory(context: Context): MoviesViewModelFactory {
        val repository = getMovieRepository(context)
        return MoviesViewModelFactory(repository)
    }
}