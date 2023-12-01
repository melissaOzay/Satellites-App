package com.example.satellites_app.features.satallite.data.local

import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.model.SatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel

interface SatelliteLocalDS {
    suspend fun insertSatelliteList(satellites: SatellitesModel)
    suspend fun getSatellites(): List<SatellitesModel>
    suspend fun getSatelliteDetail(id: Int): SatelliteDetailModel?
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailModel)
    suspend fun getSatellitePositions(id: String): List<SatellitePositionsModel>
    suspend fun insertSatellitePositions(satellitePositionsModel: SatellitePositionsModel)
}