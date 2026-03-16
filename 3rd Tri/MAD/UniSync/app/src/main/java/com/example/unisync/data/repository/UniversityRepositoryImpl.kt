package com.example.unisync.data.repository

import androidx.room.withTransaction
import com.example.unisync.data.local.UniSyncDatabase
import com.example.unisync.data.local.toDomain
import com.example.unisync.data.model.University
import com.example.unisync.data.remote.UniversityApiService
import com.example.unisync.data.remote.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UniversityRepositoryImpl @Inject constructor(
    private val apiService: UniversityApiService,
    private val database: UniSyncDatabase
) : UniversityRepository {

    override fun observeUniversities(): Flow<List<University>> {
        return database.universityDao()
            .observeUniversities()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override fun observeLastSyncTime(): Flow<Long?> {
        return database.universityDao().observeLastSyncTime()
    }

    override suspend fun refreshUniversities(country: String) {
        val syncedAt = System.currentTimeMillis()
        val remoteUniversities = apiService.getUniversities(country)
            .map { dto -> dto.toEntity(syncedAt) }

        database.withTransaction {
            database.universityDao().deleteAll()
            database.universityDao().insertAll(remoteUniversities)
        }
    }
}
