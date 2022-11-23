package com.example.effectivemobiletest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _homeDataLiveData = MutableLiveData<HomeDataResponse>()
    val homeDataLiveData: LiveData<HomeDataResponse> = _homeDataLiveData

    private val _ProductDataLiveData = MutableLiveData<ProductDetailsResponse>()
    val ProductDataLiveData: LiveData<ProductDetailsResponse> = _ProductDataLiveData

    fun refreshHome() {
        viewModelScope.launch {

            val response = repository.getHomeData()

            _homeDataLiveData.postValue(response)
        }
    }

    fun refreshProduct() {
        viewModelScope.launch {

            val prodResponse = repository.getProductDetailsData()

            _ProductDataLiveData.postValue(prodResponse)
        }
    }
}