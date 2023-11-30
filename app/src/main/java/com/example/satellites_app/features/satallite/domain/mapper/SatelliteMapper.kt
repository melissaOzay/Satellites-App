package com.example.satellites_app.features.satallite.domain.mapper

import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity
import com.example.satellites_app.features.satallite.data.model.Position
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel

fun SatelliteListEntity.toSatellite() = SatelliteListModel(
    id = satelliteId,
    active = active,
    name = name
)

fun SatelliteDetailEntity.toSatellite() = SatelliteDetailModel(
    id = satelliteId,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)

fun SatellitePositionEntity.toSatellitePosition(): SatellitePositionModel {
    return SatellitePositionModel(
        id = positionId.orEmpty(),
        positions = positions.map { position ->
            Position(position.posX, position.posY)
        }
    )
}






