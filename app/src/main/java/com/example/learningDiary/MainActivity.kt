package com.example.learningDiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.learningDiary.ui.theme.LearningDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavigation()
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

@Composable
fun DefaultPreview() {
    LearningDiaryTheme {
        Greeting()
    }
}