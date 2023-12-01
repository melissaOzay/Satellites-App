package com.example.satellites_app.features.satallite.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_list")
data class SatelliteListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val satelliteId: Int,
    val active: Boolean,
    val name: String
)
