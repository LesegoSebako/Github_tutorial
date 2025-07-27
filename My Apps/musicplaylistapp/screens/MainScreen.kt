// Name: Lesego Sebako
// Student Number: ST10493865

package com.example.musicplaylistapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musicplaylistapp.navigation.SongData
import kotlin.system.exitProcess

//Recreated repo

@Composable
fun MainScreen(navController: NavHostController) {

    //Using remember & mutable states for playlist details
    var songTitle by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    //Included padding for the app title and padding to move the app below the status bar
    Column(modifier = Modifier.padding(16.dp).padding(top = 70.dp)) {
        Text("Add to Playlist", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

    //Used outlined text fields for playlist data
        OutlinedTextField(
            value = songTitle,
            onValueChange = { songTitle = it },
            label = { Text("Song Title") })
        OutlinedTextField(
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text("Artist Name") })
        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating") })
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comments") }
        )

        if (errorMessage.isNotBlank()) {
            Text(errorMessage, color = Color.Red)

        }
        //Executed spacers to manage spacing
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val ratingInt = rating.toIntOrNull()

            //Added error handling
            if (songTitle.isBlank() || artistName.isBlank() || ratingInt == null) {
                errorMessage = "Please fill in all the fields correctly."
            } else {
                SongData.songTitle.add(songTitle)
                SongData.artistName.add(artistName)
                SongData.quantities.add(ratingInt)
                SongData.comments.add(comment)
                errorMessage = ""
                songTitle = ""
                artistName = ""
                rating = ""
                comment = ""
            }
        }) {
            Text("Add to Playlist")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate("list") }) {
            Text("View list")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {exitProcess(0)}) {
            Text("Exit App")
        }

    }
}
