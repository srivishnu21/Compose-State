package com.example.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

var names: MutableList<String> = mutableListOf<String>("Micheal", "Madan", "Arun", "Foo", "Bar")

class MainActivity : ComponentActivity() {
    private val viewModel :MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val greetingListState = remember { mutableStateListOf<String>("Micheal", "Arun") }
    val newNameState = viewModel.textFieldValue.observeAsState("")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(
            newNameState.value
        ) { newName ->
            viewModel.onTxtChange(newName)
        }
    }
}

@Composable
fun GreetingList(
    textFieldSting: String, textFieldChange: (newName: String) -> Unit
) {
//    for (name in greetingListState) {
//        Greeting(name = name)
//    }


    TextField(
        value = textFieldSting,
        onValueChange = textFieldChange
    )
    
    Greeting(name = textFieldSting)

    Button(onClick = { }) {
        Text(text = textFieldSting)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", style = MaterialTheme.typography.h5)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainScreen(viewModel)
}