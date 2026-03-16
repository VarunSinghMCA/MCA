package com.example.unisync.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {

    @Query("SELECT * FROM universities ORDER BY name ASC")
    fun observeUniversities(): Flow<List<UniversityEntity>>

    @Query("SELECT MAX(syncedAt) FROM universities")
    fun observeLastSyncTime(): Flow<Long?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<UniversityEntity>)

    @Query("DELETE FROM universities")
    suspend fun deleteAll()
}
