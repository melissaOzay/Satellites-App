package com.example.satellites_app.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "satellite_position")
data class SatellitePositionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val positionId: String? = null,
    @field:TypeConverters(SatelliteConverter::class)
    val positions: List<Position>
)
data class Position(
    val posX: Double, val posY: Double
)
