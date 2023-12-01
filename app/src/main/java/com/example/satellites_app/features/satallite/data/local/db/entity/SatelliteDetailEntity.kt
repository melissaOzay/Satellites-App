package com.example.satellites_app.features.satallite.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_detail")
data class SatelliteDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val satelliteId: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)