package com.example.satellites_app.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.data.model.SatellitePositionModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteDetailUseCase
import com.example.satellites_app.features.satallite.domain.usecase.SatellitePositionUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailVM @Inject constructor(
    private val satelliteDetailUseCase: SatelliteDetailUseCase,
    private val satellitePositionUseCase: SatellitePositionUseCase
) : BaseViewModel() {
    private val _satelliteDetail = MutableLiveData<List<SatelliteDetailModel>?>()
    val satelliteDetail = _satelliteDetail

    private val _satellitePosition = MutableLiveData<List<SatellitePositionModel>>()
    val satellitePosition = _satellitePosition

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    fun getSatellitePosition(id: String) {
        showLoading()
        viewModelScope.launch {
            hideLoading()
            val response = satellitePositionUseCase(id)
            when (response) {
                is Resource.Success -> {
                    _satellitePosition.postValue(response.data.map { it })
                }

                is Resource.Failure -> {
                    _errorMessage.postValue(response.error)
                }

            }
        }
    }

    fun getSatellites(id: Int) {
        showLoading()
        viewModelScope.launch {
            hideLoading()
            val response = satelliteDetailUseCase(id)
            when (response) {
                is Resource.Success -> {
                    _satelliteDetail.postValue(response.data)
                }

                is Resource.Failure -> {
                    _errorMessage.postValue(response.error)
                }
            }
        }
    }
}