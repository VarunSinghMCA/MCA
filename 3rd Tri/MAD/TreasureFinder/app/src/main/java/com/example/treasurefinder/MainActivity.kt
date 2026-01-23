package com.example.treasurefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.treasurefinder.ui.theme.TreasureFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasureFinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Compass(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Compass(modifier: Modifier = Modifier){
    var selectedDirection by remember { mutableStateOf("East")}
    var totalTreasureFound by remember { mutableIntStateOf(0) }
    var searchCount by remember { mutableIntStateOf(0) }

    var isTheirTreasure by remember { mutableStateOf(false) }

    LaunchedEffect(searchCount) {
        if (searchCount > 0) {
            val (treasureFound, treasureCount) = findTreasure(selectedDirection)
            isTheirTreasure = !treasureFound
            if (treasureFound) {
                totalTreasureFound += treasureCount
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4A90E2),
                        Color(0xFF7B68EE),
                        Color(0xFFE74C3C)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        // Heading
        Text(
            text="Treasure Finder",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text="Selected Direction: $selectedDirection",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Text(
            text="Treasure Found: $totalTreasureFound",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        // Showcasing if treasure is found or not
        if (isTheirTreasure){
            Text(
                text = "No Treasure Found! :(",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            Text(
                text = "We Found Treasure! :)",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    selectedDirection = "East"
                    searchCount++
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("East", fontSize = 18.sp)
            }
            Button(
                onClick = {
                    selectedDirection = "West"
                    searchCount++
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("West", fontSize = 18.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    selectedDirection = "North"
                    searchCount++
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("North", fontSize = 18.sp)
            }
            Button(
                onClick = {
                    selectedDirection = "South"
                    searchCount++
                },
                modifier = Modifier.width(120.dp)
            ) {
                Text("South", fontSize = 18.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                selectedDirection = "East"
                searchCount = 0
                totalTreasureFound = 0
            },
            modifier = Modifier.width(120.dp)
        ) {
            Text("Reset", fontSize = 18.sp)
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TreasureFinderTheme {
        Compass()
    }
}