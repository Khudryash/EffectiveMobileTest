package com.example.effectivemobiletest.data

import com.example.effectivemobiletest.cartscreen.CartResponse
import com.example.effectivemobiletest.homescreen.HomeDataResponse
import com.example.effectivemobiletest.productdetailsscreen.ProductDetailsResponse
import retrofit2.Response


class ApiClient (
    private val apiInterface: ApiInterface
) {

    suspend fun getHomeStore(): Response<HomeDataResponse> {
        return apiInterface.getHomeStore()
    }

    suspend fun getProductDetails(): Response<ProductDetailsResponse> {
        return apiInterface.getDetailsStore()
    }

    suspend fun getCart(): Response<CartResponse> {
        return apiInterface.getCart()
    }
}