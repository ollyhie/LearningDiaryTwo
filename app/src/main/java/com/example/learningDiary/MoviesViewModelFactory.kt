package com.example.learningDiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningDiary.repositories.MovieRepository
import com.example.learningDiary.viewModel.MoviesViewModel

class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java))
            return MoviesViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}