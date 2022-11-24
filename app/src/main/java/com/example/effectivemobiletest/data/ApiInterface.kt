package com.example.effectivemobiletest.data

import com.example.effectivemobiletest.cartscreen.CartResponse
import com.example.effectivemobiletest.homescreen.HomeDataResponse
import com.example.effectivemobiletest.productdetailsscreen.ProductDetailsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(value = "654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeStore(): Response<HomeDataResponse>

    @GET(value = "6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetailsStore(): Response<ProductDetailsResponse>

    @GET(value = "53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): Response<CartResponse>

}