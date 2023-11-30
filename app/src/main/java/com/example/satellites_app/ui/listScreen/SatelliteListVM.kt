package com.example.satellites_app.ui.listScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import com.example.satellites_app.features.satallite.data.model.SatelliteListModel
import com.example.satellites_app.features.satallite.domain.usecase.SatelliteListUseCase
import com.example.satellites_app.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListVM @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase,
) : BaseViewModel() {

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
            hideLoading()
            val response = satelliteListUseCase()
            when (response) {
                    is Resource.Success -> {
                        _satelliteList.postValue(response.data?.let { it })
                    }

                    is Resource.Failure -> {
                        _errorMessage.postValue(response.error)

                }

            }
        }
    }
}