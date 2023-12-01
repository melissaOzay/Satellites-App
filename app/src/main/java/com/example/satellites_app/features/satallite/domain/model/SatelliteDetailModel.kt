package com.example.satellites_app.features.satallite.domain.model

data class SatelliteDetailModel(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)
