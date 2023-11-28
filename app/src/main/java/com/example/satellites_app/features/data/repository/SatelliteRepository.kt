package com.example.satellites_app.features.data.repository

import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.features.data.local.SatelliteLocalDS
import com.example.satellites_app.utility.Resource
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository{
    fun insertSatellite(satelliteEntity:SatelliteListEntity)
    fun getSatelliteList():Flow<Resource<List<SatelliteListEntity>>>
}