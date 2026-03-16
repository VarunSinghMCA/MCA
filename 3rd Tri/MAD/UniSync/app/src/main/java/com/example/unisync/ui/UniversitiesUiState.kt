package com.example.unisync.ui

import com.example.unisync.data.model.University

sealed interface UniversitiesUiState {
    data object Loading : UniversitiesUiState

    data class Success(
        val universities: List<University>,
        val lastSyncTimeMillis: Long?
    ) : UniversitiesUiState

    data class Error(
        val message: String
    ) : UniversitiesUiState
}
