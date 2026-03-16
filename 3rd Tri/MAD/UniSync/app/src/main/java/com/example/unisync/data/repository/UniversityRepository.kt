package com.example.unisync.data.repository

import com.example.unisync.data.model.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun observeUniversities(): Flow<List<University>>
    fun observeLastSyncTime(): Flow<Long?>
    suspend fun refreshUniversities(country: String = "India")
}
