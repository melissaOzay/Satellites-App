package com.example.satellites_app.features.satallite.data.local

import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel

interface SatelliteLocalDS {
    suspend fun insertSatelliteList(satellites:SatelliteListEntity)
    suspend fun getSatellites(): List<SatelliteListModel>
    suspend fun getSatelliteDetails(id:Int): List<SatelliteDetailModel>
    suspend fun insertSatelliteDetails(satelliteDetailEntity:SatelliteDetailEntity)
    suspend fun getSatellitePositions(id:String): List<SatellitePositionModel>
    suspend fun insertSatellitePositions(satellitePositionEntity: SatellitePositionEntity)
}