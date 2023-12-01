package com.example.satellites_app.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity

@Dao
interface SatelliteDao {
    @Insert
    suspend fun insertList(satellitesEntity: SatelliteListEntity)

    @Query("SELECT * FROM satellite_list")
    suspend fun getSatelliteList(): List<SatelliteListEntity>

    @Insert
    suspend fun insertDetail(satellitesEntity: SatelliteDetailEntity)

    @Query("SELECT * FROM satellite_detail WHERE satelliteId=:satelliteId")
    suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailEntity?

    @Insert
    suspend fun insertPosition(satellitesEntity: SatellitePositionEntity)

    @Query("SELECT * FROM satellite_position WHERE positionId=:id")
    suspend fun getSatellitePosition(id: String): List<SatellitePositionEntity>

}