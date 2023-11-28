package com.example.satellites_app.features.data.repository

import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.features.data.local.SatelliteLocalDS
import com.example.satellites_app.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteLocalDs: SatelliteLocalDS
) : SatelliteRepository {
    override fun insertSatellite(satelliteEntity: SatelliteListEntity) {
        TODO("Not yet implemented")
    }

    override fun getSatelliteList(): Flow<Resource<List<SatelliteListEntity>>> {
        TODO("Not yet implemented")
    }
}