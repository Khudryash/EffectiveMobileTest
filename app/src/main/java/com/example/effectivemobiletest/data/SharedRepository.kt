package com.example.effectivemobiletest.data

import com.example.effectivemobiletest.cartscreen.CartResponse
import com.example.effectivemobiletest.homescreen.HomeDataResponse
import com.example.effectivemobiletest.productdetailsscreen.ProductDetailsResponse

class SharedRepository {

    suspend fun getHomeData(): HomeDataResponse? {
        val request = NetworkLayer.apiClient.getHomeStore()

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }

    suspend fun getProductDetailsData(): ProductDetailsResponse? {
        val request = NetworkLayer.apiClient.getProductDetails()

        if (request.isSuccessful) {
            return request.body()
        }

        return null
    }

    suspend fun getCart(): CartResponse? {
        val request = NetworkLayer.apiClient.getCart()

        if (request.isSuccessful) {
            return request.body()
        }

        return null
    }
}
