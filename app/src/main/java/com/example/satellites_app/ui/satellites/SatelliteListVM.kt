package com.example.satellites_app.ui.satellites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.domain.model.SatellitesModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteListUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListVM @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase,
) : BaseViewModel() {
    private var storedSatellites: List<SatellitesModel> = emptyList()

    private val _satellites = MutableLiveData<List<SatellitesModel>>()
    val satellites = _satellites

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage


    init {
        getSatellites()
    }

    private fun getSatellites() {
        showLoading()
        viewModelScope.launch {
            when (val response = satelliteListUseCase()) {
                is Resource.Success -> {
                    hideLoading()
                    val data = response.data
                    _satellites.postValue(data)
                    storedSatellites = data
                }

                is Resource.Failure -> {
                    hideLoading()
                    _errorMessage.postValue(response.error)
                }

            }
        }
    }

    fun filter(query: String) {
        if (query.isNotEmpty() && query.length > 1) {
            val filterList = storedSatellites.filter { satellite ->
                satellite.name.contains(query, ignoreCase = true)
            }
            _satellites.postValue(filterList)
        } else {
            _satellites.postValue(storedSatellites)
        }

    }
}