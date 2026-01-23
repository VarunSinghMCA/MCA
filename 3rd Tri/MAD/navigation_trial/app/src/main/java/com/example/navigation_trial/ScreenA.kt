package com.example.navigation_trial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFF764ba2)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "🚀 Navigation Hub",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Text(
                text = "Explore Amazing Screens",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            NavigationCard(
                title = "Screen B",
                description = "Visit Screen B with your name",
                icon = Icons.Default.AccountCircle,
                gradient = listOf(Color(0xFFf093fb), Color(0xFFf5576c)),
                onClick = { navController.navigate(Routes.SCREEN_B + "/Suresh") }
            )

            NavigationCard(
                title = "Profile",
                description = "View your awesome profile",
                icon = Icons.Default.Person,
                gradient = listOf(Color(0xFF4facfe), Color(0xFF00f2fe)),
                onClick = { navController.navigate(Routes.PROFILE + "/John_Doe/25") }
            )

            NavigationCard(
                title = "Gallery",
                description = "Explore beautiful images",
                icon = Icons.Default.Email,
                gradient = listOf(Color(0xFF43e97b), Color(0xFF38f9d7)),
                onClick = { navController.navigate(Routes.GALLERY) }
            )

            NavigationCard(
                title = "Game Zone",
                description = "Play a fun number game",
                icon = Icons.Default.Face,
                gradient = listOf(Color(0xFFfa709a), Color(0xFFfee140)),
                onClick = { navController.navigate(Routes.GAME) }
            )

            NavigationCard(
                title = "Settings",
                description = "Customize your experience",
                icon = Icons.Default.Settings,
                gradient = listOf(Color(0xFF30cfd0), Color(0xFF330867)),
                onClick = { navController.navigate(Routes.SETTINGS) }
            )

            NavigationCard(
                title = "About",
                description = "Learn more about this app",
                icon = Icons.Default.Info,
                gradient = listOf(Color(0xFFa8edea), Color(0xFFfed6e3)),
                onClick = { navController.navigate(Routes.ABOUT) }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun NavigationCard(
    title: String,
    description: String,
    icon: ImageVector,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(gradient)
                )
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
                
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Navigate",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
