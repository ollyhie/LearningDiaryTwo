package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.annotation.ExperimentalCoilApi
import com.example.learningDiary.ui.theme.LearningDiaryTheme
import com.example.learningDiary.viewModel.MoviesViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val viewModel: MoviesViewModel by viewModels()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.forEach { movie ->
                    if (movie.favoured != movie.lastState) {
                        // Navigate to the Home screen.
                    }
                }
            }
        }

         */

        setContent {
            LearningDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationController()
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