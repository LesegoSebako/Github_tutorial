package com.example.musicplaylistapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicplaylistapp.navigation.SongData

@Composable
fun DetailedViewScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp).padding(top = 70.dp)) {
        Text("Music Library", style = MaterialTheme.typography.titleLarge)

        for (i in SongData.songTitle.indices) {
            Text("${SongData.songTitle[i]} (${SongData.artistName[i]}): ${SongData.quantities[i]} - ${SongData.comments[i]}")
        }

        Spacer(modifier = Modifier.height(16.dp))


        for (i in SongData.songTitle.indices) {
            if (SongData.quantities[i] >= 2) {
                Text("${SongData.songTitle[i]}: ${SongData.quantities[i]}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("main") }) {
            Text("Back to Main")
        }
    }
}