package com.example.satellites_app.features.satallite.data.repository

import android.util.Log
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
                        satelliteLocalDs.insertSatelliteList(
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
                        satelliteLocalDs.insertSatelliteDetails(
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
                        satelliteLocalDs.insertSatellitePositions(
                            SatellitePositionEntity(
                                positionId = it.id,
                                positions = it.positions.map {position->
                                    Position(posX = position.posX, posY = position.posY)
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
            val type = object : TypeToken<List<SatelliteListModel>>() {}.type
            val satelliteList = Gson().fromJson<List<SatelliteListModel>>(read, type)
            satelliteList
        }

    }

    private suspend fun addSatelliteDetails(): List<SatelliteDetailModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_detail.json")
            val type = object : TypeToken<List<SatelliteDetailModel>>() {}.type
            val satelliteDetails = Gson().fromJson<List<SatelliteDetailModel>>(read, type)
            satelliteDetails
        }

    }

    private suspend fun addSatellitePositions(): List<SatellitePositionModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_position.json")
            val satellitePositions = Gson().fromJson(read, SatellitePositionList::class.java)
            satellitePositions.list
        }

    }

}