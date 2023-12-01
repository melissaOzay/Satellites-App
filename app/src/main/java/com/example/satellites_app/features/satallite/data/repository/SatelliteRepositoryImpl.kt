package com.example.satellites_app.features.satallite.data.repository

import com.example.satellites_app.features.satallite.data.local.SatelliteLocalDS
import com.example.satellites_app.features.satallite.data.model.JsonSatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.JsonSatelliteListModel
import com.example.satellites_app.features.satallite.data.model.JsonSatellitePositionList
import com.example.satellites_app.features.satallite.domain.mapper.convertJsonToModel
import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.model.SatellitePositionsModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel
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

    override suspend fun getSatelliteList(): Resource<List<SatellitesModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatellites = satelliteLocalDs.getSatellites()
                if (localSatellites.isEmpty()) {
                    val satelliteList = getSatellites()
                    satelliteList.map {
                        satelliteLocalDs.insertSatelliteList(it)
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

    override suspend fun getSatelliteDetail(id: Int): Resource<SatelliteDetailModel> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatelliteDetail = satelliteLocalDs.getSatelliteDetail(id)
                if (localSatelliteDetail == null) {
                    val satelliteDetail = getSatelliteDetails()
                    satelliteDetail.map {
                        satelliteLocalDs.insertSatelliteDetail(it)
                    }
                    val satelliteDetailModel = satelliteLocalDs.getSatelliteDetail(id)
                    try {
                        Resource.Success(satelliteDetailModel!!)
                    } catch (ex: Exception) {
                        Resource.Failure(Throwable(ex.message).toString())
                    }
                } else {
                    Resource.Success(localSatelliteDetail)
                }
            } catch (ex: Exception) {
                Resource.Failure(Throwable(ex.message).toString())
            }

        }
    }

    override suspend fun getSatellitePositions(id: String): Resource<List<SatellitePositionsModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val localSatellites = satelliteLocalDs.getSatellitePositions(id)
                if (localSatellites.isEmpty()) {
                    val satellitePosition = getSatellitePositions()
                    satellitePosition.map {
                        satelliteLocalDs.insertSatellitePositions(it)
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

    private suspend fun getSatellites(): List<SatellitesModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_list.json")
            val type = object : TypeToken<List<JsonSatelliteListModel>>() {}.type
            val satelliteList = Gson().fromJson<List<JsonSatelliteListModel>>(read, type)
            satelliteList.map { it.convertJsonToModel() }
        }

    }

    private suspend fun getSatelliteDetails(): List<SatelliteDetailModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_detail.json")
            val type = object : TypeToken<List<JsonSatelliteDetailModel>>() {}.type
            val satelliteDetails = Gson().fromJson<List<JsonSatelliteDetailModel>>(read, type)
            satelliteDetails.map { it.convertJsonToModel() }
        }

    }

    private suspend fun getSatellitePositions(): List<SatellitePositionsModel> {
        return withContext(Dispatchers.IO) {
            val read = ReadFile.readJsonFromAssets("satellite_position.json")
            val satellitePositions = Gson().fromJson(read, JsonSatellitePositionList::class.java)
            satellitePositions.list.map { it.convertJsonToModel() }
        }

    }

}