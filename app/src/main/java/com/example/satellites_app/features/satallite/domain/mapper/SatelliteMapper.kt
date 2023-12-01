package com.example.satellites_app.features.satallite.domain.mapper

import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionsEntity
import com.example.satellites_app.features.satallite.data.model.JsonSatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.JsonSatelliteListModel
import com.example.satellites_app.features.satallite.data.model.JsonSatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.Position
import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.model.SatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel

fun SatelliteListEntity.toSatellites() = SatellitesModel(
    id = satelliteId,
    active = active,
    name = name
)

fun SatelliteDetailEntity.toSatellites() = SatelliteDetailModel(
    id = satelliteId,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)
fun SatellitePositionsEntity.toSatellitePositions(): SatellitePositionsModel {
    return SatellitePositionsModel(
        id = positionId.orEmpty(),
        positions = positions.map { position ->
            Position(position.posX, position.posY)
        }
    )
}

fun JsonSatelliteDetailModel.convertJsonToModel() = SatelliteDetailModel(
    id = id,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)

fun JsonSatelliteListModel.convertJsonToModel() = SatellitesModel(
    id = id,
    active = active,
    name = name
)

fun JsonSatellitePositionsModel.convertJsonToModel() = SatellitePositionsModel(
    id = id,
    positions = positions.map { position ->
        Position(position.posX, position.posY)
    }
)








