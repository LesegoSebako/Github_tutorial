package com.example.fruitpickerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fruitpickerapp.ui.theme.FruitPickerAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FruitPickerAppTheme {
                FruitPickerApp()

            }
        }
    }
}
@Composable
fun FruitPickerApp(){
    val fruits = arrayOf("Apple", "Banana", "Mango", "Orange", "Strawberry")
    var selectedFruit by remember { mutableStateOf("Click to pick a fruit!")}

    Surface(
        modifier = Modifier
            .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Favorite Fruits", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))

           Text(text = selectedFruit, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                selectedFruit = fruits.random()
            }){
                Text("Show Random Fruit")
            }
        }

    }
}