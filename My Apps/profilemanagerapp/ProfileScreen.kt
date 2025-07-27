package com.example.profilemanagerapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.example.profilemanagerapp.datamodel.Profile
import androidx.compose.foundation.layout.Spacer

@Composable
fun ProfileScreen(){
    var profile by remember { mutableStateOf(Profile()) }
    var isEditMode by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        Row(

            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically


        ){
            Text("My Profile", style = MaterialTheme.typography.headlineMedium)
            Switch(
                checked = isEditMode,
                onCheckedChange = { isEditMode = it},
                modifier = Modifier.height(24.dp)

            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        if (isEditMode){
            EditProfileView(profile, onProfileUpdated = { profile = it})
        } else {
            ViewProfileView(profile)
        }
    }

}