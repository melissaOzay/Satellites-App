package com.example.satellites_app.features.satallite.data.local

import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel
import com.example.satellites_app.features.satallite.domain.mapper.toSatellite
import com.example.satellites_app.features.satallite.domain.mapper.toSatellitePosition
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteLocalDSImpl @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatelliteLocalDS {
    override suspend fun insertSatellite(satellites: SatelliteListEntity) {
        return satelliteDao.insertList(satellites)
    }

    override suspend fun getSatellites(): List<SatelliteListModel> {
        return satelliteDao.getSatelliteList().map { it.toSatellite() }
    }

    override suspend fun getSatelliteDetails(id: Int): List<SatelliteDetailModel> {
        return satelliteDao.getSatelliteDetail(id).map { it.toSatellite() }
    }

    override suspend fun insertSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity) {
        return satelliteDao.insertDetail(satelliteDetailEntity)
    }

    override suspend fun getSatellitePositions(id: String): List<SatellitePositionModel> {
        return satelliteDao.getSatellitePosition(id).map { it.toSatellitePosition() }

    }

    override suspend fun insertSatellitePosition(satellitePositionEntity: SatellitePositionEntity) {
        return satelliteDao.insertPosition(satellitePositionEntity)
    }

}