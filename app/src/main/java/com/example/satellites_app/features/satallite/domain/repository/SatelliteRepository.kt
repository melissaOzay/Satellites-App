package com.example.satellites_app.features.satallite.domain.repository


import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.model.SatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel
import com.example.satellites_app.utility.Resource

interface SatelliteRepository {
    suspend fun getSatelliteList(): Resource<List<SatellitesModel>>
    suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel>
    suspend fun getSatellitePositions(id: String): Resource<List<SatellitePositionsModel>>
}