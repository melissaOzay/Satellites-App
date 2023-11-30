package com.example.satellites_app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity

@Database(
    entities = [
        SatelliteListEntity::class,
        SatelliteDetailEntity::class,
        SatellitePositionEntity::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class SatelliteAppDB : RoomDatabase() {
    abstract fun getSatelliteDao(): SatelliteDao
}