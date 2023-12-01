package com.example.satellites_app.features.satallite.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class JsonSatelliteDetailModel(
    val id: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)
