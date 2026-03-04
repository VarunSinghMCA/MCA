package com.example.campusconnectapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campusconnectapp.R

@Composable
fun WelcomeScreen(onProceedClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // University Logo (Using a placeholder icon or text if image not available)
        Text(
            text = "University Logo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Placeholder for Department-related images/videos
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Department Image/Video Placeholder")
            }
        }

        Text(
            text = "Welcome to Campus Connect",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            onClick = onProceedClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Proceed")
        }
    }
}
