package com.example.satellites_app.features.satallite.data.repository

import com.example.satellites_app.data.local.db.entity.Position
import com.example.satellites_app.data.local.db.entity.SatelliteDetailEntity
import com.example.satellites_app.data.local.db.entity.SatelliteListEntity
import com.example.satellites_app.data.local.db.entity.SatellitePositionEntity
import com.example.satellites_app.features.satallite.data.local.SatelliteLocalDS
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionList
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel
import com.example.satellites_app.features.satallite.domain.repository.SatelliteRepository
import com.example.satellites_app.utility.ReadFile
import com.example.satellites_app.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteLocalDs: SatelliteLocalDS
) : SatelliteRepository {

    override suspend fun getSatelliteList(): Resource<List<SatelliteListModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatellites = satelliteLocalDs.getSatellites()
                if (localSatellites.isEmpty()) {
                    val satelliteList = addSatelliteList()
                    satelliteList.map {
                        satelliteLocalDs.insertSatellite(
                            SatelliteListEntity(
                                satelliteId = it.id,
                                active = it.active,
                                name = it.name
                            )
                        )
                    }
                    Resource.Success(satelliteList)
                } else {
                    Resource.Success(localSatellites)
                }
            } catch (ex: Exception) {
                Resource.Failure(Throwable(ex.message).toString())
            }
        }
    }

    override suspend fun getSatelliteDetails(id: Int): Resource<List<SatelliteDetailModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatellites = satelliteLocalDs.getSatelliteDetails(id)
                if (localSatellites.isEmpty()) {
                    val satelliteDetail = addSatelliteDetails()
                    satelliteDetail.map {
                        satelliteLocalDs.insertSatelliteDetail(
                            SatelliteDetailEntity(
                                satelliteId = it.id,
                                costPerLaunch = it.costPerLaunch,
                                firstFlight = it.firstFlight,
                                height = it.height,
                                mass = it.mass,
                            )
                        )
                    }
                    Resource.Success(satelliteDetail)
                } else {
                    Resource.Success(localSatellites)
                }
            } catch (ex: Exception) {
                Resource.Failure(Throwable(ex.message).toString())
            }
        }

    }

    override suspend fun getSatellitePositions(id: String): Resource<List<SatellitePositionModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatellites = satelliteLocalDs.getSatellitePositions(id)
                if (localSatellites.isEmpty()) {
                    val satellitePosition = addSatellitePositions()
                    satellitePosition.map {
                        satelliteLocalDs.insertSatellitePosition(
                            SatellitePositionEntity(
                                positionId = it.id,
                                positions = it.positions.map {
                                    Position(posX = it.posX, posY = it.posY)
                                }
                            )
                        )
                    }
                    Resource.Success(satellitePosition)
                } else {
                    Resource.Success(localSatellites)
                }
            } catch (ex: Exception) {
                Resource.Failure(Throwable(ex.message).toString())
            }
        }
    }

    private suspend fun addSatelliteList(): List<SatelliteListModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_list.json")
            val serializer = serializer<List<SatelliteListModel>>()
            val satelliteList = Json.decodeFromString(serializer, read)
            satelliteList
        }

    }

    private suspend fun addSatelliteDetails(): List<SatelliteDetailModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_detail.json")
            val serializer = serializer<List<SatelliteDetailModel>>()
            val satelliteDetail = Json.decodeFromString(serializer, read)
            satelliteDetail
        }

    }

    private suspend fun addSatellitePositions(): List<SatellitePositionModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_position.json")
            val serializer = serializer<SatellitePositionList>()
            val satellitePosition = Json.decodeFromString(serializer, read)
            satellitePosition.list
        }

    }

}