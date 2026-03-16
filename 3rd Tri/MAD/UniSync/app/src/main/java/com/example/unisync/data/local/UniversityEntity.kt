package com.example.unisync.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unisync.data.model.University

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey val id: String,
    val name: String,
    val website: String,
    val country: String,
    val syncedAt: Long
)

fun UniversityEntity.toDomain(): University = University(
    id = id,
    name = name,
    website = website,
    country = country
)
