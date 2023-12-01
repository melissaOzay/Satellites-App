package com.example.satellites_app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionsEntity

@Database(
    entities = [
        SatelliteListEntity::class,
        SatelliteDetailEntity::class,
        SatellitePositionsEntity::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class SatelliteAppDB : RoomDatabase() {
    abstract fun getSatelliteDao(): SatelliteDao
}