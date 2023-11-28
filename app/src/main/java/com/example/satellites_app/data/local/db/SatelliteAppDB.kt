package com.example.satellites_app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity

@Database(
    entities = [
        SatelliteListEntity::class
    ],
    version = 1
)
abstract class SatelliteAppDB : RoomDatabase() {
    abstract fun getSatelliteDao(): SatelliteDao
}