package com.example.satellites_app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel :ViewModel(){
     val loadingState=MutableLiveData<Boolean>()
    fun showLoading(){
        loadingState.value = true
    }
    fun hideLoading(){
        loadingState.value = false

    }
}