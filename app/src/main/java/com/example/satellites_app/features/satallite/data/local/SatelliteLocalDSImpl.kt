package com.example.satellites_app.features.satallite.data.local

import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel
import com.example.satellites_app.features.satallite.domain.mapper.toSatelliteDetails
import com.example.satellites_app.features.satallite.domain.mapper.toSatellitePositions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteLocalDSImpl @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatelliteLocalDS {
    override suspend fun insertSatelliteList(satellites: SatelliteListEntity) {
        return satelliteDao.insertList(satellites)
    }

    override suspend fun getSatellites(): List<SatelliteListModel> {
        return satelliteDao.getSatelliteList().map { it.toSatelliteDetails() }
    }

    override suspend fun getSatelliteDetails(id: Int): List<SatelliteDetailModel> {
        return satelliteDao.getSatelliteDetail(id).map { it.toSatelliteDetails() }
    }

    override suspend fun insertSatelliteDetails(satelliteDetailEntity: SatelliteDetailEntity) {
        return satelliteDao.insertDetail(satelliteDetailEntity)
    }

    override suspend fun getSatellitePositions(id: String): List<SatellitePositionModel> {
        return satelliteDao.getSatellitePosition(id).map { it.toSatellitePositions() }

    }

    override suspend fun insertSatellitePositions(satellitePositionEntity: SatellitePositionEntity) {
        return satelliteDao.insertPosition(satellitePositionEntity)
    }

}