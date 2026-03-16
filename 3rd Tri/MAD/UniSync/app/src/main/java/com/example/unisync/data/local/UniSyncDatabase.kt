package com.example.unisync.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UniversityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UniSyncDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}
