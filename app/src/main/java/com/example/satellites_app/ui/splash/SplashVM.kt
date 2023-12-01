package com.example.satellites_app.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.satellites_app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor() : BaseViewModel() {
    val canOpenMainScreen = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            delay(2000)
            canOpenMainScreen.value = true
        }
    }
}