package com.example.satellites_app.features.satallite.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val posX: Double,
    val posY: Double
)
@Serializable
data class SatellitePositionModel(
    val id: String,
    val positions: List<Position>
)
@Serializable
data class SatellitePositionList(val list: List<SatellitePositionModel>)