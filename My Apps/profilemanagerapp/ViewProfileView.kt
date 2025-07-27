package com.example.profilemanagerapp

import android.app.appsearch.SearchResult.MatchInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.profilemanagerapp.datamodel.Profile
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box


@Composable
fun ViewProfileView(profile: Profile){
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)){

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Image",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier =  Modifier.size(48.dp)
            )
        }

        Text(profile.name, style = MaterialTheme.typography.headlineSmall)
        Text(profile.bio, style = MaterialTheme.typography.bodyMedium)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text("Social Links", style = MaterialTheme.typography.labelLarge)
            profile.socialLinks.forEach { link ->
                if (link.isNotBlank()
                    ){Text(link, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

