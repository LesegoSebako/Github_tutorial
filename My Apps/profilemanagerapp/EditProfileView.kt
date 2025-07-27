package com.example.profilemanagerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.profilemanagerapp.datamodel.Profile
import androidx.compose.ui.draw.clip



@Composable
fun EditProfileView(
    profile: Profile,
    onProfileUpdated: (Profile) -> Unit
){
    Column (verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable {/* Open Image picker */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Image",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(48.dp)
            )
        }

            OutlinedTextField(
                value = profile.name,
                onValueChange = { onProfileUpdated(profile.copy(name = it)) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = profile.bio,
                onValueChange = { onProfileUpdated(profile.copy(bio = it)) },
                label = { Text("Bio") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Text("Social Links", style = MaterialTheme.typography.labelLarge)

            profile.socialLinks.forEachIndexed { index, link ->
            OutlinedTextField(
                value = link,
                onValueChange = { updatedLink ->
                    val updatedLinks = profile.socialLinks.toMutableList()
                    updatedLinks[index] = updatedLink
                    onProfileUpdated(profile.copy(socialLinks = updatedLinks))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                val updatedLinks = profile.socialLinks + ""
                onProfileUpdated(profile.copy(socialLinks = updatedLinks))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Social Link")

        }
    }
}