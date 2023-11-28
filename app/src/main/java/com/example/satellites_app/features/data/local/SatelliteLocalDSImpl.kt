package com.example.satellites_app.features.data.local

import com.example.satellites_app.data.local.db.dao.SatelliteDao
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatelliteLocalDSImpl @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatelliteLocalDS {
    override fun insertSatellite(satellites: SatelliteListEntity) = satelliteDao.insert(satellites)
    override fun getSatellite(): Flow<List<SatelliteListEntity>> {
        return satelliteDao.getSatellites()
    }

}