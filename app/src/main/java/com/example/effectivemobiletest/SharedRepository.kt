package com.example.effectivemobiletest

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
}
