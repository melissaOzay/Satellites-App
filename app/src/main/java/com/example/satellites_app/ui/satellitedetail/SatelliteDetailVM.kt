package com.example.satellites_app.ui.satellitedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.domain.model.Position
import com.example.satellites_app.features.satallite.domain.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteDetailUseCase
import com.example.satellites_app.features.satallite.domain.usecase.SatellitePositionsUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailVM @Inject constructor(
    private val satelliteDetailUseCase: SatelliteDetailUseCase,
    private val satellitePositionUseCase: SatellitePositionsUseCase
) : BaseViewModel() {
    private val _satelliteDetail = MutableLiveData<SatelliteDetailModel>()
    val satelliteDetail = _satelliteDetail

    private val _satellitePositions = MutableLiveData<Position>()
    val satellitePositions = _satellitePositions

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    fun getSatellitePositions(id: String?) {
        showLoading()
        viewModelScope.launch {
            when (val response = satellitePositionUseCase(id.orEmpty())) {
                is Resource.Success -> {
                    hideLoading()
                    response.data.firstOrNull()?.positions?.let { positions ->
                        for (position in positions) {
                            satellitePositions.postValue(position)
                            delay(3000)
                        }
                    }
                }

                is Resource.Failure -> {
                    hideLoading()
                    _errorMessage.postValue(response.error)
                }

            }
        }
    }

    fun getSatelliteDetail(id: Int) {
        showLoading()
        viewModelScope.launch {
            when (val response = satelliteDetailUseCase(id)) {
                is Resource.Success -> {
                    hideLoading()
                    response.data.let {
                        _satelliteDetail.postValue(it)
                    }
                }

                is Resource.Failure -> {
                    hideLoading()
                    _errorMessage.postValue(response.error)
                }
            }
        }
    }
}