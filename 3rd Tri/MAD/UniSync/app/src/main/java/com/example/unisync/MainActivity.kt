package com.example.unisync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unisync.ui.UniversitiesScreen
import com.example.unisync.ui.UniversitiesViewModel
import com.example.unisync.ui.theme.UniSyncTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniSyncTheme {
                UniversitiesRoute()
            }
        }
    }
}

@Composable
private fun UniversitiesRoute(
    viewModel: UniversitiesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UniversitiesScreen(
        uiState = uiState,
        onRetry = viewModel::refreshNow
    )
}