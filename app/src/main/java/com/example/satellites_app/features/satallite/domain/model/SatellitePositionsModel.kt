package com.example.satellites_app.features.satallite.domain.model


data class SatellitePositionsModel(
    val id: String,
    val positions: List<Position>
)
data class Position(
    val posX: Double,
    val posY: Double
)
