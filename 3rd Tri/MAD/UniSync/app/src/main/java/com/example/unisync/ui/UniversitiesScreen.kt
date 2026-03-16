package com.example.unisync.ui

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unisync.data.model.University
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversitiesScreen(
    uiState: UniversitiesUiState,
    onRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "University Portal") })
        }
    ) { innerPadding ->
        when (uiState) {
            UniversitiesUiState.Loading -> {
                LoadingContent(innerPadding)
            }

            is UniversitiesUiState.Error -> {
                ErrorContent(
                    innerPadding = innerPadding,
                    message = uiState.message,
                    onRetry = onRetry
                )
            }

            is UniversitiesUiState.Success -> {
                SuccessContent(
                    innerPadding = innerPadding,
                    universities = uiState.universities,
                    lastSyncTimeMillis = uiState.lastSyncTimeMillis
                )
            }
        }
    }
}

@Composable
private fun LoadingContent(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(
            text = "Fetching universities...",
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun ErrorContent(
    innerPadding: PaddingValues,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
private fun SuccessContent(
    innerPadding: PaddingValues,
    universities: List<University>,
    lastSyncTimeMillis: Long?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LastSyncSection(lastSyncTimeMillis = lastSyncTimeMillis)
        }

        if (universities.isEmpty()) {
            item {
                Text(
                    text = if (lastSyncTimeMillis == null) {
                        "Syncing for the first time..."
                    } else {
                        "No universities available."
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            items(universities, key = { it.id }) { university ->
                UniversityListItem(university = university)
            }
        }
    }
}

@Composable
private fun LastSyncSection(lastSyncTimeMillis: Long?) {
    Surface(
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Last Sync Time",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = formatLastSyncTime(lastSyncTimeMillis),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Composable
private fun UniversityListItem(university: University) {
    Surface(
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = university.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = university.website.ifBlank { "Website not available" },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

private fun formatLastSyncTime(syncTimeMillis: Long?): String {
    if (syncTimeMillis == null) return "Never synced"

    val relativeTime = DateUtils.getRelativeTimeSpanString(
        syncTimeMillis,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS
    )
    val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    val absoluteTime = formatter.format(Date(syncTimeMillis))
    return "${relativeTime} ($absoluteTime)"
}
