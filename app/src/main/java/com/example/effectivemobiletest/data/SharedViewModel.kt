package com.example.effectivemobiletest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.cartscreen.CartResponse
import com.example.effectivemobiletest.homescreen.HomeDataResponse
import com.example.effectivemobiletest.productdetailsscreen.ProductDetailsResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _homeDataLiveData = MutableLiveData<HomeDataResponse>()
    val homeDataLiveData: LiveData<HomeDataResponse> = _homeDataLiveData

    private val _ProductDataLiveData = MutableLiveData<ProductDetailsResponse>()
    val ProductDataLiveData: LiveData<ProductDetailsResponse> = _ProductDataLiveData

    private val _CartDataLiveData = MutableLiveData<CartResponse>()
    val CartDataLiveData: LiveData<CartResponse> = _CartDataLiveData

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

    fun refreshCart() {
        viewModelScope.launch {

            val cartResponse = repository.getCart()

            _CartDataLiveData.postValue(cartResponse)
        }
    }
}