package com.example.dynamicnamelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dynamicnamelist.ui.theme.DynamicNameListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicNameListTheme {
                DynamicNameList()
            }
        }
    }
}

@Composable
fun DynamicNameList() {
    var nameInput by remember { mutableStateOf("") }
    val names = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Enter name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (nameInput.isNotBlank()) {
                names.add(nameInput.trim())
                nameInput = ""
            }
        }) {
            Text("Add Name")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(names) { name ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(name)
                    Button(onClick = { names.remove(name) }) {
                        Text("Remove")
                    }
                }
            }
        }
    }
}