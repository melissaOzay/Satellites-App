package com.example.satellites_app.features.satallite.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JsonPosition(
    val posX: Double,
    val posY: Double
)
@Serializable
data class JsonSatellitePositionsModel(
    val id: String,
    val positions: List<JsonPosition>
)
@Serializable
data class JsonSatellitePositionList(val list: List<JsonSatellitePositionsModel>)