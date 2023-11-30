package com.example.satellites_app.features.satallite.domain.repository


import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel
import com.example.satellites_app.utility.Resource

interface SatelliteRepository {
   suspend fun getSatelliteList(): Resource<List<SatelliteListModel>>
   suspend fun getSatelliteDetails(id:Int): Resource<List<SatelliteDetailModel>>
   suspend fun getSatellitePositions(id:String): Resource<List<SatellitePositionModel>>
}