package com.example.satellites_app.features.data.local

import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import kotlinx.coroutines.flow.Flow

interface SatelliteLocalDS {
    fun insertSatellite(satellites:SatelliteListEntity)
    fun getSatellite(): Flow<List<SatelliteListEntity>>
}