package com.example.satellites_app.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDao {
    @Insert
    fun insert(satellitesEntity:SatelliteListEntity)
    @Query("SELECT * FROM satellite_List")
    fun getSatellites(): Flow<List<SatelliteListEntity>>
}