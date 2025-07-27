package com.example.diaryapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.time.LocalDate
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(currentDate: LocalDate, onDateChange: (LocalDate) -> Unit ){
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
        Button(onClick = { onDateChange(currentDate.minusDays(1))}){
            Text("<")
        }

        Text(text = currentDate.toString(),modifier = Modifier.alignByBaseline())
        Button(onClick = {onDateChange(currentDate.plusDays(1))}){
            Text(">")
        }
    }
}