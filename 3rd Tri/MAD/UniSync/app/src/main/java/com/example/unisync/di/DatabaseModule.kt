package com.example.unisync.di

import android.content.Context
import androidx.room.Room
import com.example.unisync.data.local.UniSyncDatabase
import com.example.unisync.data.local.UniversityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UniSyncDatabase {
        return Room.databaseBuilder(
            context,
            UniSyncDatabase::class.java,
            "unisync.db"
        ).build()
    }

    @Provides
    fun provideUniversityDao(database: UniSyncDatabase): UniversityDao {
        return database.universityDao()
    }
}
