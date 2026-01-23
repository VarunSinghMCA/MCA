package com.example.inputbox

//------------------------------------------------------------------
// Android / Activity
//------------------------------------------------------------------
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

//------------------------------------------------------------------
// Compose Runtime
//------------------------------------------------------------------
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

//------------------------------------------------------------------
// Layout
//------------------------------------------------------------------
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//------------------------------------------------------------------
// Interaction (Hover)
//------------------------------------------------------------------
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState

//------------------------------------------------------------------
// Material 3
//------------------------------------------------------------------
import androidx.compose.material3.*

//------------------------------------------------------------------
// Animation
//------------------------------------------------------------------
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape

//------------------------------------------------------------------
// Graphics
//------------------------------------------------------------------
import androidx.compose.ui.graphics.Color

//------------------------------------------------------------------
// Coroutines
//------------------------------------------------------------------
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InputBoxText()
        }
    }
}

@Composable
fun InputBoxText() {

    //------------------------------------------------------------------
    // State
    //------------------------------------------------------------------
    var inputBoxText by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }

    //------------------------------------------------------------------
    // Color Palette (Modern & Soft)
    //------------------------------------------------------------------
    val backgroundColor = Color(0xFFF5F7FA)
    val primaryColor = Color(0xFF3F51B5)   // Indigo
    val accentColor = Color(0xFF5C6BC0)
    val textPrimary = Color(0xFF1F2937)
    val textSecondary = Color(0xFF6B7280)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //------------------------------------------------------------------
            // Title
            //------------------------------------------------------------------
            Text(
                text = "Welcome 👋",
                fontSize = 26.sp,
                color = textPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Enter text below",
                fontSize = 16.sp,
                color = textSecondary
            )

            Spacer(modifier = Modifier.height(20.dp))

            //------------------------------------------------------------------
            // Input Field
            //------------------------------------------------------------------
            OutlinedTextField(
                value = inputBoxText,
                onValueChange = { inputBoxText = it },
                label = { Text("Type something") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    cursorColor = primaryColor
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            //------------------------------------------------------------------
            // Button Hover + Click Fade Effect
            //------------------------------------------------------------------

            // 1. Track interactions
            val interactionSource = remember { MutableInteractionSource() }

            // 2. Hover state
            val isHovered by interactionSource.collectIsHoveredAsState()

            // 3. Click animation alpha
            val clickAlpha = remember { Animatable(1f) }

            // 4. Coroutine scope
            val scope = rememberCoroutineScope()

            // 5. Hover transparency
            val hoverAlpha = if (isHovered) 0.25f else 1f

            // 6. Combine hover + click animation
            val finalAlpha = clickAlpha.value * hoverAlpha

            Button(
                onClick = {
                    displayedText = inputBoxText

                    scope.launch {
                        // Fade to 50%
                        clickAlpha.animateTo(
                            targetValue = 0.5f,
                            animationSpec = tween(875)
                        )
                        // Fade back to 100%
                        clickAlpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(875)
                        )
                    }
                },
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    containerColor = accentColor.copy(alpha = finalAlpha),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(14.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "OK",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            //------------------------------------------------------------------
            // Output Text
            //------------------------------------------------------------------
            if (displayedText.isNotEmpty()) {
                Text(
                    text = "You Entered:",
                    fontSize = 14.sp,
                    color = textSecondary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = displayedText,
                    fontSize = 18.sp,
                    color = primaryColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OkButtonPreview() {
    InputBoxText()
}
