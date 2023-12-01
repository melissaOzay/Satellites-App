package com.example.satellites_app.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.data.model.Position
import com.example.satellites_app.features.satallite.data.model.SatelliteDetailModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteDetailsUseCase
import com.example.satellites_app.features.satallite.domain.usecase.SatellitePositionsUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailVM @Inject constructor(
    private val satelliteDetailUseCase: SatelliteDetailsUseCase,
    private val satellitePositionUseCase: SatellitePositionsUseCase
) : BaseViewModel() {
    private val _satelliteDetail = MutableLiveData<List<SatelliteDetailModel>?>()
    val satelliteDetail = _satelliteDetail

    private val _satellitePosition = MutableLiveData<Position>()
    val satellitePosition = _satellitePosition

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    fun getSatellitePositions(id: String?) {
        showLoading()
        viewModelScope.launch {
            when (val response = satellitePositionUseCase(id.orEmpty())) {
                is Resource.Success -> {
                    delay(2000)
                    hideLoading()
                    response.data.firstOrNull()?.positions?.let { positions ->
                        for (position in positions) {
                            satellitePosition.postValue(position)
                            delay(if (position == positions.first()) 2000 else 3000)
                        }
                    }
                }


                is Resource.Failure -> {
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
                    delay(2000)
                    hideLoading()
                    _satelliteDetail.postValue(response.data)
                }

                is Resource.Failure -> {
                    _errorMessage.postValue(response.error)
                }
            }
        }
    }
}