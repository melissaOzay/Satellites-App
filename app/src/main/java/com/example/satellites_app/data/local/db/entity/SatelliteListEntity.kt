package com.example.satellites_app.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_List")
data class SatelliteListEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "active")
    val active: Boolean,
    @ColumnInfo(name = "name")
    val name: String,
)
