package com.example.satellites_app.features.satallite.data.local

import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.Position
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionsEntity
import com.example.satellites_app.features.satallite.domain.mapper.toSatellites
import com.example.satellites_app.features.satallite.domain.mapper.toSatellitePositions
import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.model.SatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteLocalDSImpl @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatelliteLocalDS {
    override suspend fun insertSatelliteList(satellites: SatellitesModel) {
        return satelliteDao.insertList(
            SatelliteListEntity(
                satelliteId = satellites.id,
                active = satellites.active,
                name = satellites.name
            )
        )
    }

    override suspend fun getSatellites(): List<SatellitesModel> {
        return satelliteDao.getSatelliteList().map { it.toSatellites() }
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailModel? {
        return satelliteDao.getSatelliteDetail(id)?.toSatellites()
    }

    override suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailModel) {
        return satelliteDao.insertDetail(
            SatelliteDetailEntity(
                satelliteId = satelliteDetail.id,
                costPerLaunch = satelliteDetail.costPerLaunch,
                firstFlight = satelliteDetail.firstFlight,
                height = satelliteDetail.height,
                mass = satelliteDetail.mass
            )
        )
    }

    override suspend fun getSatellitePositions(id: String): List<SatellitePositionsModel> {
        return satelliteDao.getSatellitePositions(id).map { it.toSatellitePositions() }

    }

    override suspend fun insertSatellitePositions(satellitePositionsModel: SatellitePositionsModel) {
        return satelliteDao.insertPositions(SatellitePositionsEntity(
            positionId = satellitePositionsModel.id,
            positions = satellitePositionsModel.positions.map {
                Position(posX = it.posX, posY = it.posY)
            }
        )
        )
    }

}