package com.example.unisync.data.remote

import com.example.unisync.data.local.UniversityEntity
import com.google.gson.annotations.SerializedName

data class UniversityDto(
    val name: String,
    val country: String,
    @SerializedName("web_pages") val webPages: List<String>
)

fun UniversityDto.toEntity(syncedAt: Long): UniversityEntity {
    val website = webPages.firstOrNull().orEmpty()
    val stableId = "${name.trim()}|${country.trim()}|${website.trim()}".lowercase()
    return UniversityEntity(
        id = stableId,
        name = name,
        website = website,
        country = country,
        syncedAt = syncedAt
    )
}
