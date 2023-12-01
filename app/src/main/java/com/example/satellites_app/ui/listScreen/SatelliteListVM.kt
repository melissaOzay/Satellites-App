package com.example.satellites_app.ui.listScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteListUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListVM @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase,
) : BaseViewModel() {
    private val tempSatelliteList = ArrayList<SatelliteListModel>()

    private val _satelliteList = MutableLiveData<List<SatelliteListModel>>()
    val satelliteList = _satelliteList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    init {
        getSatellites()
    }

    private fun getSatellites() {
        showLoading()
        viewModelScope.launch {
            delay(2000)
            hideLoading()
            when (val response = satelliteListUseCase()) {
                is Resource.Success -> {
                    response.data.let {
                        _satelliteList.postValue(it)
                        tempSatelliteList.addAll(it)
                    }
                }

                is Resource.Failure -> {
                    _errorMessage.postValue(response.error)
                }

            }
        }
    }

    fun filter(query: String) {
        satelliteList.value?.let {
            if (query.isNotEmpty() && query.length > 1) {
                val filterList = it.filter { satellite ->
                    satellite.name.contains(query, ignoreCase = true)
                }
                _satelliteList.postValue(filterList)
            } else {
                _satelliteList.postValue(tempSatelliteList)
            }
        }
    }
}