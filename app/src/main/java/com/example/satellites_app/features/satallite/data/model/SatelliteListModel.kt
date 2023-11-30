package com.example.satellites_app.features.satallite.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SatelliteListModel(
    val id: Int,
    val active: Boolean,
    val name: String
)
