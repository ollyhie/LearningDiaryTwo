package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.data.MovieDataBase
import com.example.learningDiary.repositories.MovieRepository
import com.example.learningDiary.ui.theme.LearningDiaryTheme
import com.example.learningDiary.utils.InjectorUtils
import com.example.learningDiary.viewModel.MoviesViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val factory = InjectorUtils.provideMoviesViewModelFactory(LocalContext.current)
                    val moviesViewModel: MoviesViewModel = viewModel(factory = factory)
                    NavigationController(moviesViewModel = moviesViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    // declare a variable
    var name by remember {
        mutableStateOf("")
    }
    Column {
        // render its value
        Text(text = "Hello ${name}!")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it }, // change its value if user enters text
            label = { Text("Name") },
            modifier = Modifier
        )
    }
}