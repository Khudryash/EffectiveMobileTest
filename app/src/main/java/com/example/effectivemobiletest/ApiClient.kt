package com.example.effectivemobiletest

import retrofit2.Response


class ApiClient (
    private val apiInterface: ApiInterface
) {

    suspend fun getHomeStore(): Response<HomeDataResponse> {
        return apiInterface.getHomeStore()
    }
}